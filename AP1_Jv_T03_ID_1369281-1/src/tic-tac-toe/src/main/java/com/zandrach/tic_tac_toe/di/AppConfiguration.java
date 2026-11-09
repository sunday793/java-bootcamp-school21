package com.zandrach.tic_tac_toe.di;

import com.zandrach.tic_tac_toe.datasource.mapper.BoardEntityMapper;
import com.zandrach.tic_tac_toe.datasource.mapper.GameEntityMapper;
import com.zandrach.tic_tac_toe.datasource.repository.GameRepository;
import com.zandrach.tic_tac_toe.datasource.repository.GameRepositoryImpl;
import com.zandrach.tic_tac_toe.datasource.storage.GameStorage;
import com.zandrach.tic_tac_toe.domain.service.GameService;
import com.zandrach.tic_tac_toe.domain.service.GameServiceImpl;
import com.zandrach.tic_tac_toe.web.mapper.BoardWebMapper;
import com.zandrach.tic_tac_toe.web.mapper.GameWebMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public BoardEntityMapper boardEntityMapper() {
        return new BoardEntityMapper();
    }

    @Bean
    public GameEntityMapper gameEntityMapper(BoardEntityMapper boardMapper) {
        return new GameEntityMapper(boardMapper);
    }

    @Bean
    public GameStorage gameStorage() {
        return new GameStorage();
    }

    @Bean
    public GameRepository gameRepository(GameStorage storage, GameEntityMapper mapper) {
        return new GameRepositoryImpl(storage, mapper);
    }

    @Bean
    public GameService gameService(GameRepository repository) {
        return new GameServiceImpl(repository);
    }

    @Bean
    public BoardWebMapper boardWebMapper() {
        return new BoardWebMapper();
    }

    @Bean
    public GameWebMapper gameWebMapper(BoardWebMapper boardWebMapper) {
        return new GameWebMapper(boardWebMapper);
    }
}
