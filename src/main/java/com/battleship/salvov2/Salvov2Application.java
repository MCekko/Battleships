package com.battleship.salvov2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Salvov2Application {

	public static void main(String[] args) {
		SpringApplication.run(Salvov2Application.class, args);



	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository) {
            return (args) -> {
			// save a couple of customers
            Player p1 = new Player("j.bauer@ctu.gov");
            Player p2 = new Player("c.obrian@ctu.gov");
            Player p3 = new Player("kim_bauer@gmail.com");
            Player p4 = new Player("t.almeida@ctu.gov");
            System.out.println(p1.toString());
            System.out.println(p2.toString());
            System.out.println(p3.toString());
            System.out.println(p4.toString());
                playerRepository.save(p1);
                playerRepository.save(p2);
                playerRepository.save(p3);
                playerRepository.save(p4);
		};
	}

}

