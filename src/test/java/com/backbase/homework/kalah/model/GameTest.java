package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void should_be_able_to_join_game() {

        //given
        Game game = new Game(1);
        Player person = new Player("dummy");

        //when
        boolean response = game.isAvailable(person);

        //then
        assertTrue(response);

    }

    @Test
    public void should_not_be_able_join_game() {

        //given
        Game game = new Game(1);
        Player person1 = new Player("dummy1");
        Player person2 = new Player("dummy2");
        Player person3 = new Player("dummy3");

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
        Player player1 = new Player("dummy1");
        Game game = player1.createGame();
        Player player2 = new Player("dummy2");
        player2.askToJoinGame(game.getId());

        //when
        player1.makeMove(game.getId(), 0,1);
        player2.makeMove(game.getId(), 4, 0);

        //then
        assertEquals(game.getBoard().getEastKalah(), 7);

    }

}
