package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import lombok.Data;

@Data
public class Player {

    private int id;
    private String name;

    public Player(String name) {
        this.name = name;
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
}
