package com.battleship.salvov2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.*;
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
                                      ShipRepository shipRepository,
                                      SalvoRepository salvoRepository,
                                      ScoreRepository scoreRepository) {

        return (args) -> {
            // save a couple of customers
            Player p1 = new Player("j.bauer@ctu.gov", "24");
            Player p2 = new Player("c.obrian@ctu.gov", "42");
            Player p3 = new Player("kim_bauer@gmail.com", "kb");
            Player p4 = new Player("t.almeida@ctu.gov", "mole");
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
            List<String> shot1 = Arrays.asList("B5", "C5", "F1");
            List<String> shot2 = Arrays.asList("B4", "B5", "B6");
            List<String> shot3 = Arrays.asList("F2", "D5");
            List<String> shot4 = Arrays.asList("E1", "H3", "A2");
            List<String> shot5 = Arrays.asList("A2", "A4", "G6");
            List<String> shot6 = Arrays.asList("B5", "D5", "C7");
            List<String> shot7 = Arrays.asList("A3", "H6");
            List<String> shot8 = Arrays.asList("C5", "C6");
            List<String> shot9 = Arrays.asList("G6", "H6", "A4");
            List<String> shot10 = Arrays.asList("H1", "H2", "H3");
            List<String> shot11 = Arrays.asList("A2", "A3", "D8");
            List<String> shot12 = Arrays.asList("E1", "F2", "G3");
            List<String> shot13 = Arrays.asList("A3", "A4", "F7");
            List<String> shot14 = Arrays.asList("B5", "C6", "H1");
            List<String> shot15 = Arrays.asList("A2", "G6", "H6");
            List<String> shot16 = Arrays.asList("C5", "C7", "D5");
            List<String> shot17 = Arrays.asList("A1", "A2", "A3");
            List<String> shot18 = Arrays.asList("B5", "B6", "C7");
            List<String> shot19= Arrays.asList("G6", "G7", "G8");
            List<String> shot20 = Arrays.asList("C6", "D6", "E6");
            List<String> shot21 = Arrays.asList("H1", "H8");
            Salvo salvo1 = new Salvo(1, shot1);
            Salvo salvo2 = new Salvo(1, shot2);
            Salvo salvo3 = new Salvo(2, shot3);
            Salvo salvo4 = new Salvo(2, shot4);
            Salvo salvo5 = new Salvo(1, shot5);
            Salvo salvo6 = new Salvo(1, shot6);
            Salvo salvo7 = new Salvo(2, shot7);
            Salvo salvo8 = new Salvo(2, shot8);
            Salvo salvo9 = new Salvo(1, shot9);
            Salvo salvo10 = new Salvo(1, shot10);
            Salvo salvo11 = new Salvo(2, shot11);
            Salvo salvo12 = new Salvo(2, shot12);
            Salvo salvo13 = new Salvo(1, shot13);
            Salvo salvo14 = new Salvo(1, shot14);
            Salvo salvo15 = new Salvo(2, shot15);
            Salvo salvo16 = new Salvo(2, shot16);
            Salvo salvo17 = new Salvo(1, shot17);
            Salvo salvo18 = new Salvo(1, shot18);
            Salvo salvo19 = new Salvo(2, shot19);
            Salvo salvo20 = new Salvo(2, shot20);
            Salvo salvo21 = new Salvo(3, shot21);
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
            gp11.addShip(s22);
            gp11.addShip(s23);
            gp13.addShip(s24);
            gp13.addShip(s25);
            gp14.addShip(s26);
            gp14.addShip(s27);
            gp1.addSalvo(salvo1);
            gp2.addSalvo(salvo2);
            gp1.addSalvo(salvo3);
            gp2.addSalvo(salvo4);
            gp3.addSalvo(salvo5);
            gp4.addSalvo(salvo6);
            gp3.addSalvo(salvo7);
            gp4.addSalvo(salvo8);
            gp5.addSalvo(salvo9);
            gp6.addSalvo(salvo10);
            gp5.addSalvo(salvo11);
            gp6.addSalvo(salvo12);
            gp7.addSalvo(salvo13);
            gp8.addSalvo(salvo14);
            gp7.addSalvo(salvo15);
            gp8.addSalvo(salvo16);
            gp9.addSalvo(salvo17);
            gp10.addSalvo(salvo18);
            gp9.addSalvo(salvo19);
            gp10.addSalvo(salvo20);
            gp10.addSalvo(salvo21);
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
            salvoRepository.save(salvo1);
            salvoRepository.save(salvo2);
            salvoRepository.save(salvo3);
            salvoRepository.save(salvo4);
            salvoRepository.save(salvo5);
            salvoRepository.save(salvo6);
            salvoRepository.save(salvo7);
            salvoRepository.save(salvo8);
            salvoRepository.save(salvo9);
            salvoRepository.save(salvo10);
            salvoRepository.save(salvo11);
            salvoRepository.save(salvo12);
            salvoRepository.save(salvo13);
            salvoRepository.save(salvo14);
            salvoRepository.save(salvo15);
            salvoRepository.save(salvo16);
            salvoRepository.save(salvo17);
            salvoRepository.save(salvo18);
            salvoRepository.save(salvo19);
            salvoRepository.save(salvo20);
            salvoRepository.save(salvo21);

            Score score1 = new Score(1.0,p1,g1);
            Score score2 = new Score(0.0,p2,g1);
            Score score3 = new Score(0.5,p1,g2);
            Score score4 = new Score(0.5,p2,g2);
            Score score5 = new Score(1.0,p2,g3);
            Score score6 = new Score(0.0,p4,g3);
            Score score7 = new Score(0.5,p2,g4);
            Score score8 = new Score(0.5,p1,g4);

            scoreRepository.save(score1);
            scoreRepository.save(score2);
            scoreRepository.save(score3);
            scoreRepository.save(score4);
            scoreRepository.save(score5);
            scoreRepository.save(score6);
            scoreRepository.save(score7);
            scoreRepository.save(score8);
        };
    }
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Player player = playerRepository.findByUser(inputName);
            if (player != null) {
                return new User(player.getUser(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/api/**").hasAnyAuthority("ADMIN")
                .antMatchers("/web/games.html").permitAll()
                .antMatchers("/web/games.js").permitAll()
                .antMatchers("/web/Main.css").permitAll()
                .antMatchers("/web/fondo.jpg").permitAll()
                .antMatchers("/rest/**").denyAll()
                .antMatchers("/api/games").permitAll()
                .antMatchers("/api/players").permitAll()
                .anyRequest().hasAuthority("USER");

                http
                .formLogin()
                .usernameParameter("name")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
    }