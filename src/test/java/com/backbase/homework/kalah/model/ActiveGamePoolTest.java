package com.backbase.homework.kalah.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActiveGamePoolTest {

    @Test
    public void should_create_game() {

        //given
        Player player =  new Player("dummy1");

        //when
        Game game = ActiveGamePool.createNewGame(player);

        //then
        assertNotNull(game);
    }

    @Test
    public void should_get_existing_game() {

        //given
        Player player =  new Player("dummy1");

        //when
        Game game = ActiveGamePool.createNewGame(player);

        //Then
        assertNotNull(ActiveGamePool.getGame(game.getId()));

    }

    @Test
    public void should_expect_null_on_non_existing_game() {

        //given

        //when

        //then
        assertNull(ActiveGamePool.getGame(3));

    }

    @Test
    public void should_get_all_active_games_list() {
        //Given
        //given
        Player player =  new Player("dummy1");

        //when
        ActiveGamePool.createNewGame(player);
        ActiveGamePool.createNewGame(player);

        //then
        assertEquals(ActiveGamePool.getAllGames().size(), 2);

    }

}