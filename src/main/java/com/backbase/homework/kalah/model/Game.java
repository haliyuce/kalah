package com.backbase.homework.kalah.model;

import lombok.Data;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Game {

    private int id;
    private Board board;

    private AtomicInteger idGenerator = new AtomicInteger();
    private Player firstPlayer;
    private Player secondPlayer;

    public Game() {
        this.id =idGenerator.incrementAndGet();
        this.board = new Board();
    }

    public Game(int gameId) {
        this.id = gameId;
    }

    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public boolean askToJoinGame(Player player) {
        if(this.firstPlayer == null) {
            this.firstPlayer = player;
            return true;
        }

        if(this.secondPlayer == null) {
            this.secondPlayer = player;
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
