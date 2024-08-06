package com.example.jerryToy_be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jerryToy_be.Entity.Destination;
import org.springframework.stereotype.Repository;

@Repository
public interface DestRepository extends JpaRepository<Destination, Long> {
    Destination findByLabel(String label);
    Destination findByLatitudeAndLongitude(double latitude, double longitude);
    Destination findByDestName(String destName);
    Destination findByAddress(String address);
    Destination findByRoadaddress(String roadaddress);
}
