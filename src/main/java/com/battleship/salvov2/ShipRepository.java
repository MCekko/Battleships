package com.battleship.salvov2;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.*;

@RepositoryRestResource
public interface ShipRepository extends JpaRepository<Ship, String> {
}