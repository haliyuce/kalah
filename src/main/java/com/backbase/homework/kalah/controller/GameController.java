package com.backbase.homework.kalah.controller;

import com.backbase.homework.kalah.exception.MissingGameException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.model.dto.CreateGameRequest;
import com.backbase.homework.kalah.model.dto.CreateGameResponse;
import com.backbase.homework.kalah.repository.GameRepository;
import com.backbase.homework.kalah.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CreateGameResponse createGame(@RequestBody CreateGameRequest createGameRequest) {

        Player player = this.playerRepository.getPlayer(createGameRequest.getPlayerId());

        Game game = gameRepository.createNewGame(player);
        game.setFirstPlayer(player);

        return new CreateGameResponse(game.getId(), player.getId());
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable int gameId) throws MissingGameException {
        return this.gameRepository.getGame(gameId);
    }

    @GetMapping
    public List<Game> getAllActiveGames() {
        return this.gameRepository.getActiveGames();
    }

}
