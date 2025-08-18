package com.devrhuan.dslist.controllers;

import com.devrhuan.dslist.dto.GameMinDTO;
import com.devrhuan.dslist.entities.Game;
import com.devrhuan.dslist.repositories.GameRepository;
import com.devrhuan.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll() {
        List<GameMinDTO> result = gameService.findAll();
        return result;
    }
}
