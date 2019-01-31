package com.battleship.salvov2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.*;
import static java.util.stream.Collectors.toList;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String user;
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    public void addPlayer(GamePlayer gameplayer) {
        gameplayer.setPlayer(this);
        gamePlayers.add(gameplayer);
    }
    @JsonIgnore
    public List<Game> getGame() {
        return gamePlayers.stream().map(sub -> sub.getGame()).collect(toList());
    }

    public Player(){}

    public Player(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getIdPlayer() {
        return id;
    }

    public void setIdPlayer(long id) {
        this.id = id;
    }

    public String toString() {
        return "Player: " +
                "Email= " + this.user;
    }
}
