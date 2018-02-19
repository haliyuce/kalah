package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActiveGamePoolTest {

    @Test
    public void should_create_game() {

        //given
        Player player =  new Player("dummy1",1);

        //when
        Game game = ActiveGamePool.createNewGame(player);

        //then
        assertNotNull(game);
    }

    @Test
    public void should_get_existing_game() throws NegativeGameResponseException{

        //given
        Player player =  new Player("dummy1",1);

        //when
        Game game = ActiveGamePool.createNewGame(player);

        //Then
        assertNotNull(ActiveGamePool.getGame(game.getId()));

    }

    @Test(expected = NegativeGameResponseException.class)
    public void should_expect_null_on_non_existing_game() throws NegativeGameResponseException {

        //given

        //when

        //then

        assertNull(ActiveGamePool.getGame(33));

    }

    @Test
    public void should_get_all_active_games_list() {
        //Given
        //given
        Player player =  new Player("dummy1",1);

        //when
        ActiveGamePool.createNewGame(player);
        ActiveGamePool.createNewGame(player);

        //then
        assertEquals(ActiveGamePool.getAllGames().size(), 2);

    }

}