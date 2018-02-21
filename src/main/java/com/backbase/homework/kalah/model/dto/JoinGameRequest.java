package com.backbase.homework.kalah.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinGameRequest {

    private int gameId;
    private int playerId;

}
