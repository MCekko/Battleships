package com.battleship.salvov2;


import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Player_id")
    private String player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Game_id")
    private String game;

    private Date date = new Date();

    public GamePlayer() {}

    public GamePlayer(String player, String game){

        this.player = player;
        this.game = game;
    }


}
