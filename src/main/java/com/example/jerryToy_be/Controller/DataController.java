package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.apiDataDTO;
import com.example.jerryToy_be.Service.ApiDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dataInit")
public class DataController {
    private final ApiDataService apiDataService;
    // RestTemplateBuilder의 build()를 사용하여 RestTemplate을 생성
    @GetMapping
    public void getApiData(@RequestParam int page, @RequestParam int amount) throws RuntimeException{
        // 초기값
        final int[] pageNum = {page};
        final int[] amountNum = {amount};
        String apiURL = "https://api.visitjeju.net/vsjApi/contents/searchList";
        // 이거 추후에 secret으로 변경해야함
        String apiKey = "제이드한테 연락주세요";
        // 타이머 선언
        Timer Scheduler = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // URL 부분
                StringBuilder urlBuilder = new StringBuilder(apiURL);
                urlBuilder.append("?"+ URLEncoder.encode("apiKey", StandardCharsets.UTF_8) + "=" + apiKey);
                urlBuilder.append("&" + URLEncoder.encode("locale", StandardCharsets.UTF_8) + "=" + "kr");
                urlBuilder.append("&" + URLEncoder.encode("page", StandardCharsets.UTF_8) + "=" + pageNum[0]);
                URL url = null;
                try {
                    url = new URL(urlBuilder.toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    // 필요한 경우 헤더 추가
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                    conn.setRequestProperty("Accept", "application/json");

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 403) {
                        System.out.println("403 Forbidden error: Access is denied");
                        return;
                    }

                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    rd.close();

                    // JSON 파싱 시작
                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject)parser.parse(result.toString());
                    JSONArray itemArr = (JSONArray)jsonObject.get("items");
                    // 데이터 양 조절
                    int quantity = itemArr.size();
                    if(amountNum[0]>itemArr.size()){
                        amountNum[0]-=itemArr.size();
                    } else if(amountNum[0]<itemArr.size()){
                        quantity = amountNum[0];
                    }
                    // 본체 데이터 array 접근
                    for(int i=0; i<quantity; i++){
                        JSONObject item = (JSONObject)itemArr.get(i);
                        System.out.println("Parsing4");
                        JSONObject contentscd_item = (JSONObject)item.get("contentscd");
                        System.out.println("Parsing5");
                        JSONObject repPhoto_item = (JSONObject)item.get("repPhoto");
                        System.out.println("Parsing6");    JSONObject repPhoto_photoId = (JSONObject) (repPhoto_item != null ? repPhoto_item.get("photoid") : null);
                        System.out.println("Parsing7");

                        String alltag = item.get("alltag") != null ? item.get("alltag").toString() : "";
                        String contentsid = item.get("contentsid") != null ? item.get("contentsid").toString() : "";
                        String contentscd_value = contentscd_item != null && contentscd_item.get("value") != null ? contentscd_item.get("value").toString() : "";
                        String contentscd_label = contentscd_item != null && contentscd_item.get("label") != null ? contentscd_item.get("label").toString() : "";
                        String contentscd_refId = contentscd_item != null && contentscd_item.get("refId") != null ? contentscd_item.get("refId").toString() : "";
                        String title = item.get("title") != null ? item.get("title").toString() : "";
                        String region1cd = item.get("region1cd") != null ? item.get("region1cd").toString() : "";
                        String region2cd = item.get("region2cd") != null ? item.get("region2cd").toString() : "";
                        String address = item.get("address") != null ? item.get("address").toString() : "";
                        String roadaddress = item.get("roadaddress") != null ? item.get("roadaddress").toString() : "";
                        String tag = item.get("tag") != null ? item.get("tag").toString() : "";
                        String introduction = item.get("introduction") != null ? item.get("introduction").toString() : "";
                        Double latitude = item.get("latitude") != null ? (Double) item.get("latitude") : 0.0;
                        Double longitude = item.get("longitude") != null ? (Double) item.get("longitude") : 0.0;
                        String postcode = item.get("postcode") != null ? item.get("postcode").toString() : "";
                        String phoneno = item.get("phoneno") != null ? item.get("phoneno").toString() : "";
                        String descseo = repPhoto_item != null && repPhoto_item.get("descseo") != null ? repPhoto_item.get("descseo").toString() : "";
                        String photoid = repPhoto_photoId != null && repPhoto_photoId.get("photoid") != null ? repPhoto_photoId.get("photoid").toString() : "";
                        String imgpath = repPhoto_photoId != null && repPhoto_photoId.get("imgpath") != null ? repPhoto_photoId.get("imgpath").toString() : "";
                        String thumbnailpath = repPhoto_photoId != null && repPhoto_photoId.get("thumbnailpath") != null ? repPhoto_photoId.get("thumbnailpath").toString() : "";

                        apiDataDTO dto = new apiDataDTO(
                                alltag,
                                contentsid,
                                contentscd_value,
                                contentscd_label,
                                contentscd_refId,
                                title,
                                region1cd,
                                region2cd,
                                address,
                                roadaddress,
                                tag,
                                introduction,
                                latitude,
                                longitude,
                                postcode,
                                phoneno,
                                descseo,
                                photoid,
                                imgpath,
                                thumbnailpath
                        );
                        apiDataService.parseAndSave(dto);
                        System.out.println("Save");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                pageNum[0]++;
                if (amountNum[0] <=0) {
                    System.out.println("Max amount reached. Cancelling the task.");
                    Scheduler.cancel();
                }
                System.out.println("End");
            }
        };
        Scheduler.scheduleAtFixedRate(task, 5000, 5000);
    }
}
