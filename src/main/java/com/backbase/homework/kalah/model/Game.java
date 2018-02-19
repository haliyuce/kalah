package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.model.rule.Rule;
import com.backbase.homework.kalah.model.rule.impl.LastStoneIntoKalahRule;
import com.backbase.homework.kalah.model.rule.impl.ParallelEmptyPitRule;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Game {

    private int id;

    @Setter(AccessLevel.NONE)
    private Board board;

    private Player firstPlayer;
    private Player secondPlayer;

    @Setter(AccessLevel.NONE)
    private Player turn = firstPlayer;

    @Setter(AccessLevel.NONE)
    private Player winner;

    private GameState state = GameState.AWAITING_PLAYER;

    private List<Rule> rules;

    public Game(int gameId) {
        this.id = gameId;
        this.board = new Board();
        this.rules = new ArrayList<Rule>() {{
            add(new LastStoneIntoKalahRule());
            add(new ParallelEmptyPitRule());
        }};
    }

    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isAvailable(Player player) {
        if(this.firstPlayer == null) {
            this.firstPlayer = player;
            return true;
        }

        if(this.secondPlayer == null) {
            this.secondPlayer = player;
            this.state = GameState.ACTIVE;
            return true;
        }

        return false;
    }

    public synchronized void applyMove(Move move) throws NegativeGameResponseException{

        if(winner != null) {
            throw new NegativeGameResponseException("Game is already finished");
        }

        move.setPlayerOrder(move.getPlayer().equals(firstPlayer)?1:2);

        MoveResponse lastStoneAction = this.board.move(move);

        this.rules.forEach(rule -> rule.checkAndApply(board, lastStoneAction, move.getPlayer(), turn));

        checkStatus();
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        this.turn = this.firstPlayer;
    }

    private void checkStatus() {
        boolean winner = true;
        for (int i=0; i<6;i++) {
            if(this.board.getPitStoneCount(i, 0) != 0) {
                winner = false;
                break;
            }
        }

        if(winner) {
            this.winner = this.board.getWestKalah()>30?firstPlayer:secondPlayer;
            this.state = GameState.FINISHED;
            return;
        }

        winner = true;

        for (int i=0; i<6;i++) {
            if(this.board.getPitStoneCount(i, 1) != 0) {
                winner = false;
                break;
            }
        }

        if(winner) {
            this.winner = this.board.getEastKalah()>30?secondPlayer:firstPlayer;
            this.state = GameState.FINISHED;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public enum GameState {
        AWAITING_PLAYER, ACTIVE, FINISHED
    }
}
