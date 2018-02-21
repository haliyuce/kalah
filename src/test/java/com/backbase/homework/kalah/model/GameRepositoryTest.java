package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.MissingGameException;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameRepositoryTest extends BaseTest {

    @Test
    public void should_create_game() {

        //given
        Player player = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player);

        //when

        //then
        assertNotNull(game);
    }

    @Test
    public void should_get_existing_game() throws MissingGameException{

        //given
        Player player = playerRepository.createPlayer("player1");
        Game game = gameRepository.createNewGame(player);

        //when

        //Then
        assertNotNull(gameRepository.getGame(game.getId()));

    }

    @Test
    public void should_expect_null_on_non_existing_game() throws MissingGameException {

        expectedEx.expect(MissingGameException.class);
        expectedEx.expectMessage("No game found with this id!!");

        //given

        //when

        //then

        assertNull(gameRepository.getGame(-1));

    }

    @Test
    public void should_get_all_active_games_list() {
        //Given
        Player player = playerRepository.createPlayer("player1");

        //when
        Game game1 = gameRepository.createNewGame(player);
        game1.setState(Game.GameState.ACTIVE);
        Game game2 = gameRepository.createNewGame(player);
        game2.setState(Game.GameState.ACTIVE);

        //then
        assertEquals(gameRepository.getActiveGames().size(), 2);

    }

}