package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.sun.org.apache.xpath.internal.operations.Neg;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GameTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void should_be_able_to_join_game() {

        //given
        Game game = new Game(1);
        Player person = new Player("dummy",1);

        //when
        boolean response = game.isAvailable(person);

        //then
        assertTrue(response);

    }

    @Test
    public void should_not_be_able_join_game() {

        //given
        Game game = new Game(1);
        Player person1 = new Player("dummy1", 1);
        Player person2 = new Player("dummy2",2);
        Player person3 = new Player("dummy3",3);

        //when
        boolean response1 = game.isAvailable(person1);
        boolean response2 = game.isAvailable(person2);
        boolean response3 = game.isAvailable(person3);

        //then
        assertTrue(response1);
        assertTrue(response2);
        assertFalse(response3);

    }

    @Test
    public void should_add_the_stones_to_kalah_if_parallel_pit_is_empty_and_last_stone_is_on_it() throws NegativeGameResponseException {

        //given
        Player player1 = new Player("dummy1", 1);
        Game game = player1.createGame();
        Player player2 = new Player("dummy2", 2);
        player2.askToJoinGame(game.getId());

        //when
        player1.makeMove(game.getId(), 1,0);
        player2.makeMove(game.getId(), 5, 1);

        //then
        assertEquals(game.getBoard().getWestKalah(), 9);

    }

    @Test
    public void should_give_the_turn_again_to_player_if_last_stone_is_on_kalah() throws NegativeGameResponseException{

        //given
        Player player1 = new Player("dummy1", 1);
        Game game = player1.createGame();
        Player player2 = new Player("dummy2", 2);

        try {
            player2.askToJoinGame(game.getId());
        } catch (NegativeGameResponseException e) {
            // no exception is expected
        }

        //when
        player1.makeMove(game.getId(), 5,0);

        //then

        assertEquals(game.getTurn(), player1);

    }

    @Test
    public void should_give_error_when_wrong_user_try_to_play() throws NegativeGameResponseException{

        expectedEx.expect(NegativeGameResponseException.class);
        expectedEx.expectMessage("This is not your turn");

        //given
        Player player1 = new Player("dummy1", 1);
        Game game = player1.createGame();
        Player player2 = new Player("dummy2", 2);

        try {
            player2.askToJoinGame(game.getId());
        } catch (NegativeGameResponseException e) {
            // no exception is expected
        }

        // when
        player2.makeMove(game.getId(), 1,1);

        //then
        // exception occurs

    }

    @Test
    public void should_give_error_when_player_tries_to_play_on_opposite_players_pits() throws NegativeGameResponseException {

        expectedEx.expect(NegativeGameResponseException.class);
        expectedEx.expectMessage("Each user can only play from his/her own pits!!");
        //given
        Player player1 = new Player("dummy1", 1);
        Game game = player1.createGame();
        Player player2 = new Player("dummy2", 2);

        try {
            player2.askToJoinGame(game.getId());
        } catch (NegativeGameResponseException e) {
            // no exception is expected
        }

        //when
        player1.makeMove(game.getId(), 0,1);

    }

}