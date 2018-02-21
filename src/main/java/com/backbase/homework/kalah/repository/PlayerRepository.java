package com.backbase.homework.kalah.repository;

import com.backbase.homework.kalah.model.Player;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PlayerRepository {

    private Map<Integer, Player> players = new HashMap<>();

    private AtomicInteger idSeq = new AtomicInteger();

    public Player getPlayer(Integer id) {
        return players.get(id);
    }

    public Player createPlayer(String name) {
        Player player = new Player(name, idSeq.incrementAndGet());

        this.players.put(player.getId(), player);

        return player;
    }

}
