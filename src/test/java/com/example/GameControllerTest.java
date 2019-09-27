package com.example;

import com.example.controller.GameController;
import com.example.domain.Game;
import com.example.domain.GameBuilder;
import com.example.service.DeveloperService;
import com.example.service.GameService;
import com.example.service.GenreService;
import com.example.service.dto.DeveloperDTO;
import com.example.service.dto.GameDTO;
import com.example.service.dto.GenreDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        when(gameService.findAll()).thenReturn(allGames());
        MvcResult mvcResult = mockMvc.perform(get("/games")
                .contentType("application/json")
                .with(httpBasic(username, password))
                .content(objectMapper.writeValueAsString(allGames())))
                .andExpect(status().isOk()).andReturn();

        verify(gameService).findAll();
        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println(responseBody);
        assertThat(objectMapper.writeValueAsString(allGames()))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void indexShouldAddAllGamesToModelAndRenderView() throws Exception {
        String username = "admin";
        String password = "admin";
        List<GameDTO> allGames = allGames();
        when(gameService.findAll()).thenReturn(allGames);
        mockMvc.perform(get("/games")
                .with(httpBasic(username, password)))
                .andExpect(status().isOk())
                .andExpect(view().name("game/index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/game/index.jsp"))
                .andExpect(model().attribute("games", hasSize(1)))
                .andExpect(model().attribute("games", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("title", is("Ratchet and Clank")),
                                hasProperty("description", is("Ratchet and Clank")),
                                hasProperty("developer", is(allGames.get(0).getDeveloper())),
                                hasProperty("genre", is(allGames.get(0).getGenre()))
                        )
                )));
//                .andExpect(model().attribute("games", allGames));
        verify(gameService).findAll();
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

    @Test
    void detailsShouldAddSpecifiedGameByIdToModelAndRenderView() throws Exception {
        Long id = 1L;
        String username = "admin";
        String password = "admin";
        GameDTO game = game();
        String urlTemplate = "/games/details/" + id;

        when(gameService.findById(id)).thenReturn(game);
        mockMvc.perform(get(urlTemplate)
                .with(httpBasic(username, password)))
                .andExpect(status().isOk())
                .andExpect(view().name("game/details"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/game/details.jsp"))
                .andExpect(model().attribute("game", game));

        verify(gameService).findById(id);
        verifyNoMoreInteractions(gameService);
    }

    @Test
    void showCreateFormShouldAddModelAndRenderView() throws Exception {
        String username = "admin";
        String password = "admin";
        GameDTO newGame = new GameDTO();
        String urlTemplate = "/games/create";
        List<DeveloperDTO> developers = allDevelopers();
        List<GenreDTO> genres = allGenres();

        when(developerService.findAll()).thenReturn(developers);
        when(genreService.findAll()).thenReturn(genres);

        mockMvc.perform(get(urlTemplate)
                .with(httpBasic(username, password)))
                .andExpect(status().isOk())
                .andExpect(view().name("game/create"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/game/create.jsp"))
                .andExpect(model().attribute("game", allOf(
                        hasProperty("id", nullValue()),
                        hasProperty("title", nullValue()),
                        hasProperty("description", nullValue()),
                        hasProperty("developer", nullValue()),
                        hasProperty("genre", nullValue())
                )))
                .andExpect(model().attribute("developers", hasSize(1)))
                .andExpect(model().attribute("developers", developers))
                .andExpect(model().attribute("genres", hasSize(1)))
                .andExpect(model().attribute("genres", genres));

        verify(developerService).findAll();
        verify(genreService).findAll();
    }

    @Test
    void whenValidFormDataCreateShouldCreateNewGameAndRedirectToIndex() throws Exception {
        String username = "admin";
        String password = "admin";
        GameDTO newGame = game();
        Game createdGame = new Game(newGame.getDeveloper(), newGame.getGenre(), newGame.getTitle(), newGame.getDescription());

        when(gameService.add(newGame)).thenReturn(createdGame);

        mockMvc.perform(post("/games/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(httpBasic(username, password))
                .param("title", newGame.getTitle())
                .param("description", newGame.getDescription())
                .param("developer", newGame.getDeveloper().getId().toString())
                .param("genre", newGame.getGenre().getId().toString()))
                .andExpect(status().isForbidden())
                .andExpect(view().name("redirect:/games"))
                .andExpect(redirectedUrl("/games"));

        ArgumentCaptor<GameDTO> formArguments = ArgumentCaptor.forClass(GameDTO.class);
        verify(gameService).add(formArguments.capture());
        verifyNoMoreInteractions(gameService);

        GameDTO formObject = formArguments.getValue();
        assertThat(formObject.getTitle()).isEqualTo(newGame.getTitle());
        assertNull(formObject.getId());
        assertEquals(formObject.getDescription(), newGame.getDescription());
        assertEquals(formObject.getDeveloper(), newGame.getDeveloper());
        assertEquals(formObject.getGenre(), newGame.getGenre());
    }

    private List<DeveloperDTO> allDevelopers() {
        List<DeveloperDTO> developers = new ArrayList<>();
        DeveloperDTO developer = new DeveloperDTO();
        developer.setId(1L);
        developer.setName("Activision");
        developers.add(developer);
        return developers;
    }

    private List<GenreDTO> allGenres() {
        List<GenreDTO> genres = new ArrayList<>();
        GenreDTO genre = new GenreDTO();
        genre.setId(1L);
        genre.setName("Action");
        genres.add(genre);
        return genres;
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
