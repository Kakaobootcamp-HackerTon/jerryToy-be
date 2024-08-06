package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.apiDataDTO;
import com.example.jerryToy_be.Repository.DestRepository;
import com.example.jerryToy_be.Repository.TagRepository;
import com.example.jerryToy_be.Service.ApiDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DataController {
    private final ApiDataService apiDataService;
    public void getApiData(String page) throws Exception{
        // 초기값
        String apiURL = "https://api.visitjeju.net/vsjApi/contents/searchList";
        String apiKey = "04d34b73ce994cbd9e243123276ebe1c";
        // URL 부분
        StringBuilder urlBuilder = new StringBuilder(apiURL);
        urlBuilder.append("?"+ URLEncoder.encode("apiKey", StandardCharsets.UTF_8) + "=" + apiKey);
        urlBuilder.append("&" + URLEncoder.encode("locale", StandardCharsets.UTF_8) + "=" + "kr");
        URL url = new URL(urlBuilder.toString());

        String result;
        try{
            // 연결
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            log.info("Response Code: "+connection.getResponseCode());

            // 버퍼로 받아옴
            BufferedReader rd;
            if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                log.info(rd.toString());
            }

            // 버퍼에서 문자열으로 받아옴
            result = rd.readLine();

            // JSON 파싱 시작
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject)parser.parse(result);
            log.info(jsonObject.toJSONString());
            JSONObject itemArr = (JSONObject)jsonObject.get("items");
            // 본체 데이터 array 접근
            for(int i=0; i<itemArr.size(); i++){
                JSONObject item = (JSONObject)itemArr.get(i);
                JSONObject contentscd_item = (JSONObject)item.get("contentscd");
                JSONObject repPhoto_item = (JSONObject)item.get("repPhoto");
                JSONObject repPhoto_photoId = (JSONObject)repPhoto_item.get("photoid");
                apiDataDTO dto = new apiDataDTO(
                        (String)item.get("alltag"),
                        (String)item.get("contentsid"),
                        (String)contentscd_item.get("value"),
                        (String)contentscd_item.get("label"),
                        (String)contentscd_item.get("refId"),
                        (String)item.get("title"),
                        (String)item.get("region1cd"),
                        (String)item.get("region2cd"),
                        (String)item.get("address"),
                        (String)item.get("roadaddress"),
                        (String)item.get("tag"),
                        (String)item.get("introduction"),
                        (Double)item.get("latitude"),
                        (Double)item.get("longitude"),
                        (String)item.get("postcode"),
                        (String)item.get("phoneno"),
                        (String)repPhoto_item.get("descseo"),
                        (String)repPhoto_photoId.get("photoid"),
                        (String)repPhoto_photoId.get("imgpath"),
                        (String)repPhoto_photoId.get("thumbnailpath"));
                apiDataService.parseAndSave(dto);
            }
            rd.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
