package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import lombok.Data;

import java.util.Objects;

@Data
public class Player {

    private int id;
    private String name;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Game createGame() {
        return ActiveGamePool.createNewGame(this);
    }

    public boolean askToJoinGame(int gameId) throws NegativeGameResponseException {
        Game game = ActiveGamePool.getGame(gameId);

        if(game == null) {
            throw new NegativeGameResponseException("Game not found");
        }

        return game.isAvailable(this);
    }

    public void makeMove(int gameId, int startPitIndexX, int startPitIndexY) throws NegativeGameResponseException {
        Move move = new Move(startPitIndexX, startPitIndexY, this);
        ActiveGamePool.getActiveGame(gameId).applyMove(move);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }

}
