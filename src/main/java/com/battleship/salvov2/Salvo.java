package com.battleship.salvov2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.stream.Location;
import java.lang.reflect.Type;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="GamePlayer_id")
    private GamePlayer gamePlayer;

    private int turn;

    @ElementCollection
    @Column(name="location")
    private List<String> listShot = new ArrayList<>();

    public Salvo (){}

    public Salvo(int turn, List shot) {
        this.turn = turn;
        this.listShot = shot;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<String> getListShot() {
        return listShot;
    }
    public void setListShot(List<String> listShot) {
        this.listShot = listShot;
    }
}

