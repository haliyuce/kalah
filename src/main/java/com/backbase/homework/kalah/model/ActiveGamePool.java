package com.backbase.homework.kalah.model;

import java.util.ArrayList;
import java.util.List;

public class ActiveGamePool {

    private static List<Game> activeGames = new ArrayList<>();

    public static Game createNewGame(Player player) {
        Game newGame = new Game();
        newGame.setFirstPlayer(player);
        activeGames.add(newGame);
        return newGame;
    }

    public static Game getGame(int gameId) {
        int index = activeGames.indexOf(new Game(gameId));

        if(index > -1) {
            return activeGames.get(index);
        }

        return null;
    }

    public static List<Game> getAllGames() {
        return activeGames;
    }
}
