package com.example.first_swing_app.repositories;

import com.example.first_swing_app.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location, Long> {

}
