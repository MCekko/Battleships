package com.battleship.salvov2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.*;
@SpringBootApplication
public class Salvov2Application {

	public static void main(String[] args) {
		SpringApplication.run(Salvov2Application.class, args);

	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
                                      GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository,
                                      ShipRepository shipRepository) {

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
            Game g5 = new Game();
            Game g6 = new Game();
            Game g7 = new Game();
            Game g8 = new Game();
            gameRepository.save(g1);
            gameRepository.save(g2);
            gameRepository.save(g3);
            gameRepository.save(g4);
            gameRepository.save(g5);
            gameRepository.save(g6);
            gameRepository.save(g7);
            gameRepository.save(g8);
            List<String> location1 = Arrays.asList("H2","H3","H4");
            List<String> location2 = Arrays.asList("E1","F1","G1");
            List<String> location3 = Arrays.asList("B4","B5");
            List<String> location4 = Arrays.asList("B5","C5","D5");
            List<String> location5 = Arrays.asList("F1","F2");
            List<String> location7 = Arrays.asList("C6","C7");
            List<String> location8 = Arrays.asList("A2","A3","A4");
            List<String> location9 = Arrays.asList("G6","H6");
            Ship s1 = new Ship("destroyer",location1);
            Ship s2 = new Ship("Submarine",location2);
            Ship s3 = new Ship("Patrol Boat",location3);
            Ship s4 = new Ship("destroyer",location4);
            Ship s5 = new Ship("Patrol Boat",location5);
            Ship s6 = new Ship("destroyer",location4);
            Ship s7 = new Ship("Patrol Boat",location7);
            Ship s8 = new Ship("Submarine",location8);
            Ship s9 = new Ship("Patrol Boat",location9);
            Ship s10 = new Ship("destroyer",location4);
            Ship s11 = new Ship("Patrol Boat",location7);
            Ship s12 = new Ship("Submarine",location8);
            Ship s13 = new Ship("Patrol Boat",location9);
            Ship s14 = new Ship("destroyer",location4);
            Ship s15 = new Ship("Patrol Boat",location7);
            Ship s16 = new Ship("Submarine",location8);
            Ship s17 = new Ship("Patrol Boat",location9);
            Ship s18 = new Ship("destroyer",location4);
            Ship s19 = new Ship("Patrol Boat",location7);
            Ship s20 = new Ship("Submarine",location8);
            Ship s21 = new Ship("Patrol Boat",location9);
            Ship s22 = new Ship("destroyer",location4);
            Ship s23 = new Ship("Patrol Boat",location7);
            Ship s24 = new Ship("destroyer",location4);
            Ship s25 = new Ship("Patrol Boat",location7);
            Ship s26 = new Ship("Submarine",location8);
            Ship s27 = new Ship("Patrol Boat",location9);
            GamePlayer gp1 = new GamePlayer(p1,g1);
            GamePlayer gp2 = new GamePlayer(p2,g1);
            GamePlayer gp3 = new GamePlayer(p1,g2);
            GamePlayer gp4 = new GamePlayer(p2,g2);
            GamePlayer gp5 = new GamePlayer(p2,g3);
            GamePlayer gp6 = new GamePlayer(p4,g3);
            GamePlayer gp7 = new GamePlayer(p2,g4);
            GamePlayer gp8 = new GamePlayer(p1,g4);
            GamePlayer gp9 = new GamePlayer(p4,g5);
            GamePlayer gp10 = new GamePlayer(p1,g5);
            GamePlayer gp11 = new GamePlayer(p3,g6);
            GamePlayer gp12 = new GamePlayer(p4,g7);
            GamePlayer gp13 = new GamePlayer(p3,g8);
            GamePlayer gp14 = new GamePlayer(p4,g8);
            gamePlayerRepository.save(gp1);
            gamePlayerRepository.save(gp2);
            gamePlayerRepository.save(gp3);
            gamePlayerRepository.save(gp4);
            gamePlayerRepository.save(gp5);
            gamePlayerRepository.save(gp6);
            gamePlayerRepository.save(gp7);
            gamePlayerRepository.save(gp8);
            gamePlayerRepository.save(gp9);
            gamePlayerRepository.save(gp10);
            gamePlayerRepository.save(gp11);
            gamePlayerRepository.save(gp12);
            gamePlayerRepository.save(gp13);
            gamePlayerRepository.save(gp14);
            gp1.addShip(s1);
            gp1.addShip(s2);
            gp1.addShip(s3);
            gp2.addShip(s4);
            gp2.addShip(s5);
            gp3.addShip(s6);
            gp3.addShip(s7);
            gp4.addShip(s8);
            gp4.addShip(s9);
            gp5.addShip(s10);
            gp5.addShip(s11);
            gp6.addShip(s12);
            gp6.addShip(s13);
            gp7.addShip(s14);
            gp7.addShip(s15);
            gp8.addShip(s16);
            gp8.addShip(s17);
            gp9.addShip(s18);
            gp9.addShip(s19);
            gp10.addShip(s20);
            gp10.addShip(s21);
            gp12.addShip(s22);
            gp12.addShip(s23);
            gp13.addShip(s24);
            gp13.addShip(s25);
            gp14.addShip(s26);
            gp14.addShip(s27);
            shipRepository.save(s1);
            shipRepository.save(s2);
            shipRepository.save(s3);
            shipRepository.save(s4);
            shipRepository.save(s5);
            shipRepository.save(s6);
            shipRepository.save(s7);
            shipRepository.save(s8);
            shipRepository.save(s9);
            shipRepository.save(s10);
            shipRepository.save(s11);
            shipRepository.save(s12);
            shipRepository.save(s13);
            shipRepository.save(s14);
            shipRepository.save(s15);
            shipRepository.save(s16);
            shipRepository.save(s17);
            shipRepository.save(s18);
            shipRepository.save(s19);
            shipRepository.save(s20);
            shipRepository.save(s21);
            shipRepository.save(s22);
            shipRepository.save(s23);
            shipRepository.save(s24);
            shipRepository.save(s25);
            shipRepository.save(s26);
            shipRepository.save(s27);

        };
    }
}



