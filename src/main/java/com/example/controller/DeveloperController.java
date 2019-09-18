package com.example.controller;

import com.example.service.DeveloperService;
import com.example.service.GameService;
import com.example.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("developers")
public class DeveloperController {
    private final DeveloperService developerService;
    private final GameService gameService;
    private final GenreService genreService;

    public DeveloperController(DeveloperService developerService, GameService gameService, GenreService genreService) {
        this.developerService = developerService;
        this.gameService = gameService;
        this.genreService = genreService;
    }

    public String index(Model model) {
        model.addAttribute("developers", developerService.findAll());
        return "developer/index";
    }
}
