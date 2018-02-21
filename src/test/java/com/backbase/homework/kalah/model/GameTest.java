package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.InvalidMoveException;
import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.exception.WrongUserTurnException;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest extends BaseTest {

    @Test
    public void should_equals_and_hashcode_method_give_true_if_games_are_identical() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game1 = gameRepository.createNewGame(player);
        Game game2 = gameRepository.createNewGame(player);
        game2.setId(game1.getId());

        //then
        assertTrue(game1.equals(game2));
        assertTrue(game1.hashCode() == game2.hashCode());

    }

    @Test
    public void should_winner_should_be_null_at_creation() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game = gameRepository.createNewGame(player);

        //then
        assertNull(game.getWinner());

    }

    @Test
    public void should_game_id_be_initialized() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game = gameRepository.createNewGame(player);

        //then
        assertTrue(game.getId()>-1);

    }

    @Test
    public void should_validators_be_set_at_game_creation() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game = gameRepository.createNewGame(player);

        //then
        assertTrue(game.getValidators().size()>0);

    }

    @Test
    public void should_postprocessors_be_set_at_game_creation() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game = gameRepository.createNewGame(player);

        //then
        assertTrue(game.getPostProcessors().size()>0);

    }

    @Test
    public void should_be_able_to_join_game() {

        //given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game = gameRepository.createNewGame(player);
        boolean response = game.askToJoin(player);

        //then
        assertTrue(response);

    }

    @Test
    public void should_not_be_able_join_game() {

        //given
        Player player = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player);
        Player person2 = playerRepository.createPlayer("player2");
        Player person3 = playerRepository.createPlayer("player3");

        //when
        boolean response2 = game.askToJoin(person2);
        boolean response3 = game.askToJoin(person3);

        //then
        assertTrue(response2);
        assertFalse(response3);

    }

    @Test
    public void should_add_the_stones_to_west_kalah_if_parallel_pit_is_empty_and_last_stone_is_on_it() throws NegativeGameResponseException {

        //given
        Player player1 = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player1);
        Player player2 = playerRepository.createPlayer("player2");
        game.askToJoin(player2);

        //when
        game.applyMove(new Move(1,0, player1));
        game.applyMove(new Move(5,1, player2));

        //then
        assertEquals(game.getBoard().getWestKalah(), 9);

    }

    @Test
    public void should_add_the_stones_to_east_kalah_if_parallel_pit_is_empty_and_last_stone_is_on_it() throws NegativeGameResponseException {

        //given
        Player player1 = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player1);
        Player player2 = playerRepository.createPlayer("player2");
        game.askToJoin(player2);

        //when
        game.applyMove(new Move(4,0, player1));
        game.applyMove(new Move(5,1, player2));
        game.applyMove(new Move(1,0, player1));

        //then
        assertEquals(game.getBoard().getEastKalah(), 9);

    }

    @Test
    public void should_give_the_turn_again_to_player_if_last_stone_is_on_kalah() throws NegativeGameResponseException{

        //given
        Player player1 = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player1);
        Player player2 = playerRepository.createPlayer("player2");
        game.askToJoin(player2);

        //when
        game.applyMove(new Move(5,0, player1));

        //then

        assertEquals(game.getTurn(), player1);

    }

    @Test
    public void should_give_error_when_wrong_user_try_to_play() throws NegativeGameResponseException{

        expectedEx.expect(WrongUserTurnException.class);
        expectedEx.expectMessage("This is not your turn");

        //given
        Player player1 = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player1);
        Player player2 = playerRepository.createPlayer("player2");
        game.askToJoin(player2);

        // when
        game.applyMove(new Move(1,1, player2));

        //then
        // exception occurs

    }

    @Test
    public void should_give_error_when_player_tries_to_play_on_opposite_players_pits() throws NegativeGameResponseException {

        expectedEx.expect(InvalidMoveException.class);
        expectedEx.expectMessage("Each user can only play from his/her own pits!!");

        //given
        Player player1 = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player1);
        Player player2 = playerRepository.createPlayer("player2");
        game.askToJoin(player2);

        //when
        game.applyMove(new Move(0,1, player1));

    }

}