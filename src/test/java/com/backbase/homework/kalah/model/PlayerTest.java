package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void should_be_able_to_create_a_new_game() {
        //Given
        Player player = new Player("player1");

        //When
        Game game = player.createGame();

        //Then
        assertNotNull(game);
        assertTrue(game.getId() > 0);
        assertNotNull(game.getBoard());

    }

    @Test
    public void should_ask_a_game_to_join_and_be_successful() {

        //Given
        Player player = new Player("player1");

        //When
        player.createGame();
        boolean response = false;
        try {
            response = player.askToJoinGame(1);
        } catch (NegativeGameResponseException e) {
            //No exception is expected
            e.printStackTrace();
        }

        //Then
        assertTrue(response);

    }

    @Test
    public void should_ask_a_game_to_join_and_get_rejected() {

        //Given
        Player playerGameCreator = new Player("player1");
        Player playerGameAttendor = new Player("player1");

        Player playerGameJoinDemander = new Player("player1");

        //When
        Game game = playerGameCreator.createGame();
        try {
            boolean responseAccepted = playerGameAttendor.askToJoinGame(game.getId());
            boolean responseRejected = playerGameJoinDemander.askToJoinGame(game.getId());

            //Then
            assertTrue(responseAccepted);
            assertFalse(responseRejected);
        } catch (Throwable e) {
            //No exception is expected
            e.printStackTrace();
        }


    }

}
