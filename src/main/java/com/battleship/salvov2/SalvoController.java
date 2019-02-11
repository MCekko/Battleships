package com.battleship.salvov2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/games")
    public List<Map<String, Object>> getAllGames() {
        return gameRepository.findAll().stream().map(sub -> makeGamesDTO(sub)).collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{gameplayerID}")
    public Map<String, Object> getIDGamePlayer(@PathVariable Long gameplayerID)  {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        GamePlayer gamePlayer = gamePlayerRepository.findOne(gameplayerID);
        Set<Ship> ships = gamePlayer.getShips();
        Set<Salvo> salvos = gamePlayer.getSalvos();
        Game game = gamePlayer.getGame();
        Set<GamePlayer> gamePlayers = game.getGamePlayers();
        dto.put("Game", makeGamesDTO(gamePlayer.getGame()));
        dto.put("Ship", ships.stream().map(s -> makeShipDTO(s)).collect(Collectors.toList()));
        dto.put("Salvoes", gamePlayers.stream().map(gp -> makeSalvoDTO(gp.getSalvos())).collect(Collectors.toList()));
        return dto;
    }
}
