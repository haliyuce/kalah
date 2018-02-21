package com.backbase.homework.kalah.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateGameResponse {

    private int gameId, playerId;

    public CreateGameResponse(int gameId, int playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }
}
