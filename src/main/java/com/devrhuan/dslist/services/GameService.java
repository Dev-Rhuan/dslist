package com.devrhuan.dslist.services;

import com.devrhuan.dslist.dto.GameDTO;
import com.devrhuan.dslist.dto.GameMinDTO;
import com.devrhuan.dslist.entities.Game;
import com.devrhuan.dslist.entities.GameList;
import com.devrhuan.dslist.projections.GameMinProjection;
import com.devrhuan.dslist.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }

    @Transactional
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional
    public List<GameMinDTO> findByList(long listId) {
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

}
