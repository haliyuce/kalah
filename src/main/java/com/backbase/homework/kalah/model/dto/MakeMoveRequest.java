package com.backbase.homework.kalah.model.dto;

import lombok.Data;

@Data
public class MakeMoveRequest {

    private int gameId, startPitIndexX, startPitIndexY, playerId;
}
