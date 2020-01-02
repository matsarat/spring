package com.trzewik.spring.domain.board;

import com.trzewik.spring.domain.game.Game;
import com.trzewik.spring.domain.game.GameException;
import com.trzewik.spring.domain.game.GameRepository;
import com.trzewik.spring.domain.game.Result;
import com.trzewik.spring.domain.player.PlayerRepository;

import java.util.List;

public interface GameService {
    Game createGame();

    Game addPlayer(String gameId, String playerName) throws GameException, GameRepository.GameNotFoundException;

    Game startGame(String gameId) throws GameRepository.GameNotFoundException, GameException;

    Game makeMove(String gameId, String playerId, Game.Move move) throws GameRepository.GameNotFoundException, PlayerRepository.PlayerNotFoundException, GameException;

    List<Result> getGameResults(String gameId) throws GameRepository.GameNotFoundException, GameException;
}