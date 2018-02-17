package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;

public class Player {

    private int id;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Game createGame() {
        Game newGame = ActiveGamePool.createNewGame(this);
        return newGame;
    }

    public boolean askToJoinGame(int gameId) throws NegativeGameResponseException {
        Game game = ActiveGamePool.getGame(gameId);

        if(game == null) {
            throw new NegativeGameResponseException("Game not found");
        }

        return game.askToJoinGame(this);
    }
}
