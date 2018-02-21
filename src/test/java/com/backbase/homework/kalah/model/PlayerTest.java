package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.ImmatureGameStateException;
import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.Assert.*;

public class PlayerTest extends BaseTest{

    @Test
    public void should_id_attr_give_validation_errors_if_id_not_valid() {
        //Given
        Player player = new Player( "dummy1",-1);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        //when
        Set<ConstraintViolation<Player>> violations = validator.validate(player);

        //then
        assertFalse(violations.isEmpty());

    }

    @Test
    public void should_name_attribute_give_validation_errors_if_id_not_valid() {
        //Given
        Player player = new Player( null,5);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        //when
        Set<ConstraintViolation<Player>> violations = validator.validate(player);

        //then
        assertFalse(violations.isEmpty());

    }

    @Test
    public void should_equals_hashcode_give_expected_values() {
        //Given
        Player player1 = new Player( "dummy",5);
        Player player2 = new Player( "dummy",5);

        //when
        boolean resp = player1.equals(player2);
        boolean hashcodeResp = player1.hashCode() == player2.hashCode();

        //then
        assertTrue(resp);
        assertTrue(hashcodeResp);
    }

    @Test
    public void should_be_able_to_create_a_new_game() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //When
        Game game = gameRepository.createNewGame(player);

        //Then
        assertNotNull(game);
        assertTrue(game.getId() > 0);
        assertNotNull(game.getBoard());

    }

    @Test
    public void should_ask_a_game_to_join_and_be_successful() {

        //Given
        Player player = playerRepository.createPlayer("player1");

        Player player2 = playerRepository.createPlayer("player2");

        //When
        Game game = gameRepository.createNewGame(player);
        boolean response = false;

        response = game.askToJoin(player2);

        //Then
        assertTrue(response);

    }

    @Test
    public void should_ask_a_game_to_join_and_get_rejected() throws NegativeGameResponseException {

        //Given
        Player playerGameCreator = playerRepository.createPlayer("player1");
        Player playerGameAttender = playerRepository.createPlayer("player2");

        Player rejectedPlayer = playerRepository.createPlayer("player3");

        //When
        Game game = gameRepository.createNewGame(playerGameCreator);

        boolean responseAccepted = game.askToJoin(playerGameAttender);
        boolean responseRejected = game.askToJoin(rejectedPlayer);

        //Then
        assertTrue(responseAccepted);
        assertFalse(responseRejected);

    }

    @Test
    public void should_have_an_exception_on_awaiting_player_game() throws NegativeGameResponseException {

        expectedEx.expect(ImmatureGameStateException.class);
        expectedEx.expectMessage("Game needs another player to start");

        //given
        Player player = playerRepository.createPlayer("dummy1");
        Game game = gameRepository.createNewGame(player);

        //when
        game.applyMove(new Move(0, 0, player));

        //then
    }

    @Test
    public void should_make_a_successful_move() throws NegativeGameResponseException{
        //given
        Player player = playerRepository.createPlayer("dummy1");
        Game game = gameRepository.createNewGame(player);

        Player player2 = playerRepository.createPlayer("dummy2");
        game.askToJoin(player2);

        //when
        game.applyMove(new Move(0, 0, player));

        //then
        assertEquals(game.getBoard().getWestKalah(), 1);

    }

    @Test
    public void should_win_after_this_move() throws NegativeGameResponseException{

        //given
        Player player = playerRepository.createPlayer("dummy1");
        Game game = gameRepository.createNewGame(player);

        Player player2 = playerRepository.createPlayer("dummy2");
        game.askToJoin(player2);

        game.getBoard().resetPitStoneCount(1,0);
        game.getBoard().resetPitStoneCount(2,0);
        game.getBoard().resetPitStoneCount(3,0);
        game.getBoard().resetPitStoneCount(4,0);
        game.getBoard().resetPitStoneCount(5,0);
        game.getBoard().setWestKalah(30);

        //when
        game.applyMove(new Move(0, 0, player));

        //then
        assertEquals(game.getState(), Game.GameState.FINISHED);
        assertEquals(game.getWinner(), player);

    }

}
