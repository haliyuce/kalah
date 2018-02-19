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
    public void should_ask_a_game_to_join_and_get_rejected() throws NegativeGameResponseException {

        //Given
        Player playerGameCreator = new Player("player1");
        Player playerGameAttender = new Player("player1");

        Player rejectedPlayer = new Player("player1");

        //When
        Game game = playerGameCreator.createGame();

        boolean responseAccepted = playerGameAttender.askToJoinGame(game.getId());
        boolean responseRejected = rejectedPlayer.askToJoinGame(game.getId());

        //Then
        assertTrue(responseAccepted);
        assertFalse(responseRejected);

    }

    @Test(expected = NegativeGameResponseException.class)
    public void should_have_an_exception_on_awaiting_player_game() throws NegativeGameResponseException {
        //given
        Player player = new Player("dummy1");
        player.createGame();

        //when
        player.makeMove(100, 0, 0);

        //then
    }

    @Test
    public void should_make_a_successful_move() throws NegativeGameResponseException{
        //given
        Player player = new Player("dummy1");
        Game game = player.createGame();

        Player player2 = new Player("dummy2");
        player2.askToJoinGame(game.getId());

        //when
        player.makeMove(game.getId(), 0, 0);

        //then
        assertEquals(game.getBoard().getWestKalah(), 1);

    }

    @Test
    public void should_win_after_this_move() throws NegativeGameResponseException{

        //given
        Player player = new Player("dummy1");
        Game game = player.createGame();

        Player player2 = new Player("dummy2");
        player2.askToJoinGame(game.getId());

        //when
        player2.makeMove(game.getId(),3,1);
        player.makeMove(game.getId(),4,1);
        player2.makeMove(game.getId(),5,1);
        player.makeMove(game.getId(),0,1);
        player2.makeMove(game.getId(),1,1);
        player.makeMove(game.getId(),2,1);
        player2.makeMove(game.getId(),3,1);
        player.makeMove(game.getId(),4,1);
        player2.makeMove(game.getId(),5,1);

        //then
        assertEquals(game.getState(), Game.GameState.FINISHED);
        assertEquals(game.getWinner(), player);

    }

}
