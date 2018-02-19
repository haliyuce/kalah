package com.backbase.homework.kalah.service.rule;

import com.backbase.homework.kalah.model.Board;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;

/**
 * this interface makes the indication for the after-move processes like clarifying that if there is a winner,
 * or the processes based on the place of the last stone
 */
public interface PostProcessors {
    void checkAndApply(Game game, MoveResponse lastStoneAction, Player player);
}
