package com.zandrach.tic_tac_toe.web.controller;

import com.zandrach.tic_tac_toe.domain.model.Game;
import com.zandrach.tic_tac_toe.domain.service.GameService;
import com.zandrach.tic_tac_toe.web.mapper.GameWebMapper;
import com.zandrach.tic_tac_toe.web.model.GameDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final GameWebMapper mapper;

    public GameController(GameService gameService, GameWebMapper mapper) {
        this.gameService = gameService;
        this.mapper = mapper;
    }

    @PostMapping("/{uuid}")
    public GameDto play(@PathVariable UUID uuid, @RequestBody GameDto request) {
        Game incoming = mapper.toDomain(request, uuid);
        Game updated = gameService.play(incoming);
        return mapper.toDto(updated);
    }

    @PostMapping
    public GameDto create() {
        Game game = gameService.createGame();
        return mapper.toDto(game);
    }

    @GetMapping("/{uuid}")
    public GameDto get(@PathVariable UUID uuid) {
        Game game = gameService.findById(uuid);
        return mapper.toDto(game);
    }

}
