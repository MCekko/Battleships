package com.battleship.salvov2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
@SpringBootApplication
public class Salvov2Application {

	public static void main(String[] args) {
		SpringApplication.run(Salvov2Application.class, args);



	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository) {
        return (args) -> {
            // save a couple of customers
            Player p1 = new Player("j.bauer@ctu.gov");
            Player p2 = new Player("c.obrian@ctu.gov");
            Player p3 = new Player("kim_bauer@gmail.com");
            Player p4 = new Player("t.almeida@ctu.gov");
            playerRepository.save(p1);
            playerRepository.save(p2);
            playerRepository.save(p3);
            playerRepository.save(p4);
            Game g1 = new Game();
            Game g2 = new Game();
            Game g3 = new Game();
            Game g4 = new Game();
            gameRepository.save(g1);
            gameRepository.save(g2);
            gameRepository.save(g3);
            gameRepository.save(g4);
        };
    }
}



