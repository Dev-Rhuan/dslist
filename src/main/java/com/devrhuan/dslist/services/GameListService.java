package com.devrhuan.dslist.services;

import com.devrhuan.dslist.dto.GameListDTO;
import com.devrhuan.dslist.entities.GameList;
import com.devrhuan.dslist.projections.GameMinProjection;
import com.devrhuan.dslist.repositories.GameListRepository;

import com.devrhuan.dslist.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int targetIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(targetIndex, obj);

        int min = sourceIndex < targetIndex ? sourceIndex : targetIndex;
        int max = sourceIndex < targetIndex ? targetIndex : sourceIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }
}
