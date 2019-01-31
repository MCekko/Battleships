package com.battleship.salvov2;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.stream.Location;
import java.lang.reflect.Type;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String shipType;

    @ElementCollection
    @Column(name="location")
    private List<String> listPosition = new ArrayList<>();

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="GamePlayer_id")
    private GamePlayer gamePlayer;

public Ship (){}

    public Ship(String ship) {
        this.shipType = ship;
    }

    public long getIdShip() {
        return id;
    }

    public void setIdShip(long id) {
        this.id = id;
    }


    public String getShipType() {
        return shipType;
    }

    public GamePlayer getGamePlayer(){
    return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }


    public List<String> getListPosition() {
        return listPosition;
    }

    public void setListPosition(List<String> listPosition) {
        this.listPosition = listPosition;
    }
}
