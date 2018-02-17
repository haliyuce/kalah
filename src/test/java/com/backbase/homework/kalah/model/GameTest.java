package com.backbase.homework.kalah.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void should_be_able_to_join_game() {

        //given
        Game game = new Game();
        Player person = new Player("dummy");

        //when
        boolean response = game.askToJoinGame(person);

        //then
        assertTrue(response);

    }

    @Test
    public void should_not_be_able_join_game() {

        //given
        Game game = new Game();
        Player person1 = new Player("dummy1");
        Player person2 = new Player("dummy2");
        Player person3 = new Player("dummy3");

        //when
        boolean response1 = game.askToJoinGame(person1);
        boolean response2 = game.askToJoinGame(person2);
        boolean response3 = game.askToJoinGame(person3);

        //then
        assertTrue(response1);
        assertTrue(response2);
        assertFalse(response3);

    }

}
