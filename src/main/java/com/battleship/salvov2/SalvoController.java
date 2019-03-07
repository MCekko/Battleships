package com.battleship.salvov2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;
import java.net.Authenticator;
import java.security.acl.Owner;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class SalvoController {


    ///inyeccion de dependencias
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private SalvoRepository salvoRepository;

    private Map<String, Object> makeGamesDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("Date", game.getDate());
        dto.put("GamePlayers", game.getGamePlayers().stream().map(gamePlayer -> makeGamePlayerDTO(gamePlayer)).collect(toList()));
        return dto;
    }
    private Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getIdPlayer());
        dto.put("email", player.getUser());
        return dto;
    }
    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getIdGamePLayer());
        dto.put("Player", makePlayerDTO(gamePlayer.getPlayer()));

        return dto;
    }

    private Map<String, Object> makeShipDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("Type", ship.getShipType());
        dto.put("Location",ship.getListPosition());
        return dto;
    }

    private Map<String, Object> makeShotDTO(Salvo salvo) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("Turn", salvo.getTurn());
        dto.put("Player", salvo.getGamePlayer().getPlayer().getIdPlayer());
        dto.put("Location",salvo.getListShot());
        return dto;
    }
    private Map<String, Object> makeSalvoDTO(Set<Salvo> salvo) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("salvo", salvo.stream().map(sv -> makeShotDTO(sv)).collect(Collectors.toList()));

        return dto;
    }
    public Map<String, Object> makeScoresDto(Set<Score> scores ) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        int won=0;
        int lost=0;
        int tied=0;
        double total=0;
        String player="";

        for(Score score : scores) {
            if (score.getScore() == 1) {
                won += 1;
            } else if (score.getScore() == 0.5) {
                tied += 1;
            } else if (score.getScore() == 0) {
                lost += 1;
            } else {
                System.out.print("Set a correct number!!!!");
            }
            player = score.getPlayer().getUser();
            total += score.getScore();
        }
        dto.put("player", player);
        dto.put("won", won);
        dto.put("lost", lost);
        dto.put("tied", tied);
        dto.put("total", total);
        System.out.print(total);
        return dto;
    }
    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private Player getCurrentUser(Authentication authentication) {
        return playerRepo.findByUser(authentication.getName());
    }

    @RequestMapping("/games")
    public Map<String, Object> getAllGames(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        if (authentication != null){
            dto.put("PlayerLogin", makePlayerDTO(getCurrentUser(authentication)));
        }else if (authentication == null){
            dto.put("PlayerLogin", null);
        }

        dto.put("games", gameRepository.findAll().stream().map(sub -> makeGamesDTO(sub)).collect(Collectors.toList()));
        dto.put("leaderBoard",playerRepo.findAll().stream().map(pl -> makeScoresDto(pl.getScores())).collect(Collectors.toList()));
        return dto;
    }



    @RequestMapping("/game_view/{gameplayerID}")
    public Map<String, Object> getIDGamePlayer(@PathVariable Long gameplayerID)  {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        GamePlayer gamePlayer = gamePlayerRepository.findOne(gameplayerID);
        Set<Ship> ships = gamePlayer.getShips();
        Set<Salvo> salvos = gamePlayer.getSalvos();

        System.out.println("Pregiunto por el... " + gamePlayer.toString());
        Game game = gamePlayer.getGame();
        System.out.println("Hola este es mi Game " + game);


        Set<GamePlayer> gamePlayers =
                game
                        .getGamePlayers();
        dto.put("Game", makeGamesDTO(game));
        dto.put("Ship", ships.stream().map(s -> makeShipDTO(s)).collect(Collectors.toList()));
        dto.put("Salvoes", gamePlayers.stream().map(gp -> makeSalvoDTO(gp.getSalvos())).collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestBody Player player) {

        if (player.getUser().isEmpty() || player.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (playerRepo.findByUser(player.getUser()) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        playerRepo.save(new Player(player.getUser(), player.getPassword()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)

    public ResponseEntity<Object> createdGame( Authentication authentication) {

        if (authentication != null){
            Game newGame = new Game();
            gameRepository.save(newGame);
            GamePlayer newGamePlayer = new GamePlayer(getCurrentUser(authentication), newGame);
            gamePlayerRepository.save(newGamePlayer);
            return  new ResponseEntity<>(new HashMap<String, Long>()
            {{put("GpID", newGamePlayer.getIdGamePLayer());}}, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("You need be loged", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(path = "/game/{gameID}/players", method = RequestMethod.POST)

    public ResponseEntity<Object> findGame(@PathVariable long gameID, Authentication authentication){

        System.out.println("Id a buscar: " + gameID);
        Game game = gameRepository.findOne(gameID);
        System.out.println("Juego: " + game);
        Player player = getCurrentUser(authentication);
        GamePlayer newGameplayer2 = new GamePlayer();

        game.addGamePlayer(newGameplayer2);
        player.addGamePlayer(newGameplayer2);

        return new ResponseEntity<>(new HashMap<String, Long>(){{
            put("gameID", gamePlayerRepository.save(newGameplayer2).getIdGamePLayer());
        }}, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/games/players/{gamePlayerId}/ships", method = RequestMethod.POST)
    public ResponseEntity<Object> addships(@PathVariable long gamePlayerId, @RequestBody List<Ship> ships
            ,Authentication authentication){
        if (getCurrentUser(authentication) == null) {

            return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);
        }
        GamePlayer gp = gamePlayerRepository.findOne(gamePlayerId);
        if (gp.getShips().size() > 0){

            return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);
        }

        for (Ship ship:ships){
            gp.addShip(ship);
        }
        shipRepository.save(ships);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
