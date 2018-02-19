package com.backbase.homework.kalah.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Move {

    private int startPitIndexX, startPitIndexY;

    // for game logs printing
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Player player;

    @JsonIgnore
    private int playerOrder;

    public Move(int startPitIndexX, int startPitIndexY, Player player) {

        this.startPitIndexX = startPitIndexX;
        this.startPitIndexY = startPitIndexY;
        this.player = player;
    }

}
