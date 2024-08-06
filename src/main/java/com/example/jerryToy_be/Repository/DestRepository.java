package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestRepository extends JpaRepository<Destination, Long> {
    Destination findByTag(String tag);
    Destination findByLatitudeAndLongitude(double latitude, double longitude);
    Destination findByDestName(String destName);
    Destination findByAddress(String address);
    Destination findByRoadaddress(String roadaddress);
}
