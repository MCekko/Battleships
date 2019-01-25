package com.battleship.salvov2;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String user;
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> players;

    public void addPlayer(Player player) {
//        player.setSubscriber(this);
//        player.add(subscription);
    }

    public Player(){}

    public Player(String email) {
        this.user = email;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String toString() {
        return "Player: " +
                "Email= " + this.user;
    }
}
