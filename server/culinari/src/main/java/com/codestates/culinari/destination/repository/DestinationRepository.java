package com.codestates.culinari.destination.repository;

import com.codestates.culinari.destination.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("select d from Destination d where d.profile.id = :profileId")
    List<Destination> findAllByProfileId(@Param("profileId") Long profileId);
}
