package com.backbase.homework.kalah.controller;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.model.dto.JoinGameRequest;
import com.backbase.homework.kalah.model.dto.MakeMoveRequest;
import com.backbase.homework.kalah.repository.GameRepository;
import com.backbase.homework.kalah.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int signUp(@RequestAttribute String playerName) {
        Player player = playerRepository.createPlayer(playerName);
        return player.getId();
    }

    @PutMapping("/joingame")
    @ResponseBody
    public boolean joinGame(@RequestBody JoinGameRequest joinGameRequest) throws NegativeGameResponseException {
        Player player = this.playerRepository.getPlayer(joinGameRequest.getPlayerId());

        return gameRepository.getGame(joinGameRequest.getGameId()).askToJoin(player);
    }

    @PutMapping("/makemove")
    public void makeMove(@RequestBody  MakeMoveRequest makeMoveRequest) throws NegativeGameResponseException {
        gameRepository.getGame(makeMoveRequest.getGameId())
                      .applyMove(new Move(makeMoveRequest.getStartPitIndexX(),
                                          makeMoveRequest.getStartPitIndexY(),
                                          this.playerRepository.getPlayer(makeMoveRequest.getPlayerId())));
    }

}
