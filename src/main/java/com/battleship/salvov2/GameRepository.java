package com.battleship.salvov2;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Player> findByDate(String date);
}
