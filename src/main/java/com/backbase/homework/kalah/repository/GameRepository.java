package com.backbase.homework.kalah.repository;

import com.backbase.homework.kalah.exception.MissingGameException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class GameRepository {

    private List<Game> games = new ArrayList<>();

    private AtomicInteger gameIdGenerator = new AtomicInteger();

    public Game createNewGame(Player player) {
        Game newGame = new Game(gameIdGenerator.incrementAndGet());
        newGame.setFirstPlayer(player);
        games.add(newGame);
        return newGame;
    }

    public Game getGame(int gameId) throws MissingGameException {
        int index = games.indexOf(new Game(gameId));

        if(index > -1) {
            return games.get(index);
        }

        throw new MissingGameException("No game found with this id!!");
    }

    public List<Game> getAllGames() {
        return games;
    }

    public List<Game> getActiveGames() {
        return this.games.stream().filter(p -> p.getState() == Game.GameState.ACTIVE).collect(Collectors.toList());
    }
}
