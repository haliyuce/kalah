package com.backbase.homework.kalah.model;

import com.backbase.homework.kalah.repository.GameRepository;
import com.backbase.homework.kalah.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class BaseTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    protected PlayerRepository playerRepository;
    protected GameRepository gameRepository;

    @Before
    public void init() {
        this.playerRepository = new PlayerRepository();
        this.gameRepository = new GameRepository();
    }

}
