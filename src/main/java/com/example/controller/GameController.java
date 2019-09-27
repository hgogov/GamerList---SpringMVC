package com.example.controller;

import com.example.service.DeveloperService;
import com.example.service.GameService;
import com.example.service.GenreService;
import com.example.service.dto.GameDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final DeveloperService developerService;
    private final GenreService genreService;

    public GameController(GameService gameService, DeveloperService developerService, GenreService genreService) {
        this.gameService = gameService;
        this.developerService = developerService;
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<GameDTO> allGames = gameService.findAll();
        model.addAttribute("games", allGames);
        return "game/index";
        // return new ModelAndView("game-index", "games", model).addObject("games",
        // allGames);
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Long id, Model model) {
        GameDTO game = gameService.findById(id);
        model.addAttribute("game", game);
        return "game/details";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(@ModelAttribute("game") GameDTO gameDTO, Model model) {
        if (gameDTO == null) {
            gameDTO = new GameDTO();
        }
        model.addAttribute("game", new GameDTO());
        model.addAttribute("developers", developerService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "game/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("game") GameDTO gameDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("game", gameDTO); // revisit
            model.addAttribute("developers", developerService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "game/create";
        }
        gameService.add(gameDTO);
        return "redirect:/games";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        GameDTO gameDTO = gameService.findById(id);
        model.addAttribute("game", gameDTO);
        model.addAttribute("developers", developerService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("id", id);
        return "game/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("game") GameDTO gameDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("developers", developerService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "game/edit";
        }
//        Game game = new Game(gameDTO.getDeveloper(), gameDTO.getGenre(), gameDTO.getTitle(), gameDTO.getDescription());
        gameService.update(id, gameDTO);
        return "redirect:/games/details/" + id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameService.findById(id));
        return "game/delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id) {
        gameService.delete(id);
        return "redirect:/games";
    }
}
