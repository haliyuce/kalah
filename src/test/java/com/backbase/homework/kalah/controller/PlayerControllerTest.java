package com.backbase.homework.kalah.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.backbase.homework.kalah.model.dto.CreateGameRequest;
import com.backbase.homework.kalah.model.dto.CreateGameResponse;
import com.backbase.homework.kalah.model.dto.JoinGameRequest;
import com.backbase.homework.kalah.model.dto.MakeMoveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_sign_up_successfully() throws Exception {

        //then
        mvc.perform(post("/api/v1/player").contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("playerName", "dummy1")).andExpect(status().is2xxSuccessful());

    }

    @Test
    public void should_join_a_game_successfully() throws Exception {

        //create Player11
        MvcResult result = mvc.perform(post("/api/v1/player").contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("playerName", "dummy1")).andExpect(status().is2xxSuccessful()).andReturn();

        int playerId1 = Integer.valueOf(result.getResponse().getContentAsString());

        //Create Game
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.setPlayerId(playerId1);
        MvcResult creategameResponseRaw = mvc.perform(post("/api/v1/game")
                                                        .content(this.mapper.writeValueAsString(createGameRequest))
                                             .contentType(MediaType.APPLICATION_JSON_VALUE))
                                             .andExpect(status().is2xxSuccessful()).andReturn();

        CreateGameResponse createGameResponse = mapper.readValue(creategameResponseRaw.getResponse().getContentAsString(), CreateGameResponse.class);

        //Create Player2
        result = mvc.perform(post("/api/v1/player").contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("playerName", "dummy2")).andExpect(status().is2xxSuccessful()).andReturn();

        int playerId2 = Integer.valueOf(result.getResponse().getContentAsString());

        //Try to join the game
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.setGameId(createGameResponse.getGameId());
        joinGameRequest.setPlayerId(playerId2);
        result = mvc.perform(put("/api/v1/player/joingame").contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                        .content(mapper.writeValueAsString(joinGameRequest)))
                     .andExpect(status().is2xxSuccessful()).andReturn();

        assertTrue(mapper.readValue(result.getResponse().getContentAsString(), Boolean.class));

    }

    @Test
    public void should_make_a_successful_move() throws Exception {

        //create Player11
        MvcResult result = mvc.perform(post("/api/v1/player").contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("playerName", "dummy1")).andExpect(status().is2xxSuccessful()).andReturn();

        int playerId1 = Integer.valueOf(result.getResponse().getContentAsString());

        //Create Game
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.setPlayerId(playerId1);
        MvcResult creategameResponseRaw = mvc.perform(post("/api/v1/game")
                .content(this.mapper.writeValueAsString(createGameRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful()).andReturn();

        CreateGameResponse createGameResponse = mapper.readValue(creategameResponseRaw.getResponse().getContentAsString(), CreateGameResponse.class);

        //Create Player2
        result = mvc.perform(post("/api/v1/player").contentType(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr("playerName", "dummy2")).andExpect(status().is2xxSuccessful()).andReturn();

        int playerId2 = Integer.valueOf(result.getResponse().getContentAsString());

        //Try to join the game
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.setGameId(createGameResponse.getGameId());
        joinGameRequest.setPlayerId(playerId2);
        result = mvc.perform(put("/api/v1/player/joingame").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(joinGameRequest)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        assertTrue(mapper.readValue(result.getResponse().getContentAsString(), Boolean.class));

        //make a move
        MakeMoveRequest makeMoveRequest = new MakeMoveRequest();
        makeMoveRequest.setPlayerId(playerId1);
        makeMoveRequest.setGameId(createGameResponse.getGameId());
        makeMoveRequest.setStartPitIndexX(0);
        makeMoveRequest.setStartPitIndexY(0);

        mvc.perform(put("/api/v1/player/makemove").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(makeMoveRequest)))
                .andExpect(status().is2xxSuccessful());

    }

}
