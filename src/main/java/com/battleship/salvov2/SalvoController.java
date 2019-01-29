package com.battleship.salvov2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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


    @RequestMapping("/games")
    public List<Map<String, Object>> getAllGames() {
        return gameRepository.findAll().stream().map(sub -> makeGamesDTO(sub)).collect(Collectors.toList());
    }

}
