package com.trzewik.spring.infrastructure.db.repository;

import com.trzewik.spring.domain.game.Game;
import com.trzewik.spring.domain.game.GameRepository;
import com.trzewik.spring.infrastructure.db.dao.Dao;
import com.trzewik.spring.infrastructure.db.dto.GameDto;
import com.trzewik.spring.infrastructure.db.model.GameEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class GameRepoImpl implements GameRepository {
    private Dao<GameEntity> dao;

    @Override
    public void save(Game game) {
        dao.save(new GameEntity(GameDto.from(game)));
    }

    @Override
    public Optional<Game> findById(String id) {
        Optional<GameEntity> optional = dao.get(id);
        return optional.map(GameEntity::getGame).map(GameDto::to);
    }

    @Override
    public void update(Game game) {
        dao.update(new GameEntity(GameDto.from(game)));
    }
}
