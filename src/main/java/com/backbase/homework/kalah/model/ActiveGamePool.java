package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ActiveGamePool {

    private static List<Game> activeGames = new ArrayList<>();

    private static AtomicInteger gameIdGenerator = new AtomicInteger();

    public static Game createNewGame(Player player) {
        Game newGame = new Game(gameIdGenerator.incrementAndGet());
        newGame.setFirstPlayer(player);
        activeGames.add(newGame);
        return newGame;
    }

    public static Game getGame(int gameId) throws NegativeGameResponseException {
        int index = activeGames.indexOf(new Game(gameId));

        if(index > -1) {
            return activeGames.get(index);
        }

        throw new NegativeGameResponseException("No game found with this id!!");
    }

    public static List<Game> getAllGames() {
        return activeGames;
    }
}
