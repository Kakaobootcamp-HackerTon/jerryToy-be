package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestRepository extends JpaRepository<Destination, Long> {

    @Query(value = "SELECT d FROM Destination d WHERE d.label = :label")
    List<Destination> findAllByLabel(@Param("label") String label);

    Destination findByLatitudeAndLongitude(double latitude, double longitude);
    Destination findByDestName(String destName);
    Destination findByAddress(String address);
    Destination findByRoadaddress(String roadaddress);
}
