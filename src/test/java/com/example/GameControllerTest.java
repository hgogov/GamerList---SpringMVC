package com.example;

import com.example.controller.GameController;
import com.example.domain.Game;
import com.example.domain.GameBuilder;
import com.example.service.DeveloperService;
import com.example.service.GameService;
import com.example.service.GenreService;
import com.example.service.dto.GameDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.hamcrest.beans.HasProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameController.class)
@OverrideAutoConfiguration(enabled = true)
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ActiveProfiles(value = "test")
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @MockBean
    private DeveloperService developerService;

    @MockBean
    private GenreService genreService;

    @Test
    void shouldReturnAllGames() throws Exception {
        String username = "admin";
        String password = "admin";
        when(gameService.findAllGames()).thenReturn(allGames());
        MvcResult mvcResult = mockMvc.perform(get("/games")
                .contentType("application/json")
                .with(httpBasic(username, password))
                .content(objectMapper.writeValueAsString(allGames())))
                .andExpect(status().isOk()).andReturn();

        verify(gameService).findAllGames();
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println(responseBody);
        assertThat(objectMapper.writeValueAsString(allGames()))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void indexShouldAddGamesToModelAndRenderView() throws Exception {
        String username = "admin";
        String password = "admin";
        List<GameDTO> allGames = allGames();
        when(gameService.findAllGames()).thenReturn(allGames);
        mockMvc.perform(get("/games")
                .with(httpBasic(username, password)))
                .andExpect(status().isOk())
                .andExpect(view().name("game/index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/game/index.jsp"))
                .andExpect(model().attribute("games", hasSize(1)))
//                .andExpect(model().attribute("games", hasItem(
//                        allOf(
//                                hasProperty("id", is(1L)),
//                                hasProperty("title", is("Ratchet and Clank")),
//                                hasProperty("description", is("Ratchet and Clank")),
//                                hasProperty("developer", )),
//                                hasProperty("genre", is(allGames().get(0).getGenre()))
//                        )
//                ))
                .andExpect(model().attribute("games", allGames));
        verify(gameService).findAllGames();
        verifyNoMoreInteractions(gameService);
    }

    @Test
    void detailsShouldReturnStatusOk() throws Exception {
        Long id = 1L;
        String username = "admin";
        String password = "admin";
        when(gameService.findById(id)).thenReturn(game());

        MvcResult mvcResult = mockMvc.perform(get("/games/details/" + id)
                .accept(MediaType.TEXT_PLAIN)
                .contentType(MediaType.TEXT_PLAIN)
                .with(httpBasic(username, password))
                .content(objectMapper.writeValueAsString(game())))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
        verify(gameService).findById(id);
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println(responseBody);
        GameDTO actualObject = objectMapper.readValue(responseBody, GameDTO.class);
        assertThat(game()).isEqualToComparingFieldByField(actualObject);
    }

    private GameDTO game() {
        Game game = new GameBuilder()
                .newGame(1L, "Ratchet and Clank")
                .withDeveloper(1L, "Activision")
                .withGenre(1L, "Action")
                .build();
        return new GameDTO(game);
    }

    private List<GameDTO> allGames() {
        List<GameDTO> games = new ArrayList<>();
        Game game = new GameBuilder()
                .newGame(1L, "Ratchet and Clank")
                .withDeveloper(1L, "Activision")
                .withGenre(1L, "Action")
                .withDescription("Ratchet and Clank")
                .build();
        games.add(new GameDTO(game));
        return games;
    }

}
