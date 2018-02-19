package com.backbase.homework.kalah.model.rule;

import com.backbase.homework.kalah.model.Board;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;

public interface Rule {
    void checkAndApply(Board board, MoveResponse lastStoneAction, Player player, Player turn);
}
