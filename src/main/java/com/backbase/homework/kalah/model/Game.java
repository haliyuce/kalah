package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.service.rule.PostProcessors;
import com.backbase.homework.kalah.service.rule.impl.LastStoneIntoKalahPostProcessor;
import com.backbase.homework.kalah.service.rule.impl.ParallelEmptyPitPostProcessor;
import com.backbase.homework.kalah.service.validation.Validator;
import com.backbase.homework.kalah.service.validation.impl.CheckGameEndValidator;
import com.backbase.homework.kalah.service.validation.impl.RightUserTurnValidator;
import com.backbase.homework.kalah.service.validation.impl.ValidMoveValidation;
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

    private Player turn = firstPlayer;

    @Setter(AccessLevel.NONE)
    private Player winner;

    private GameState state = GameState.AWAITING_PLAYER;

    private List<PostProcessors> postProcessors;

    private List<Validator> validators;

    public Game(int gameId) {
        this.id = gameId;
        this.board = new Board();
        this.postProcessors = new ArrayList<PostProcessors>() {{
            add(new LastStoneIntoKalahPostProcessor());
            add(new ParallelEmptyPitPostProcessor());
        }};

        this.validators = new ArrayList<Validator>() {{
           add(new CheckGameEndValidator());
           add(new RightUserTurnValidator());
           add(new ValidMoveValidation());
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

        move.setPlayerOrder(move.getPlayer().equals(firstPlayer)?1:2);

        for(Validator validator: validators) {
            validator.validate(this, move);
        }

        MoveResponse lastStoneAction = this.board.move(move);

        this.postProcessors.forEach(processor -> processor.checkAndApply(this, lastStoneAction, move.getPlayer()));

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
