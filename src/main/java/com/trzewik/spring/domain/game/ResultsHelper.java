package com.trzewik.spring.domain.game;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultsHelper {

    static List<Result> createResults(final Game game) throws Result.Exception {
        validateGameStatus(game);
        return generateResults(game);
    }

    private static void validateGameStatus(final Game game) throws Result.Exception {
        if (!game.getStatus().isEnded()) {
            throw new Result.Exception("Results are available only when game finished. Please continue auction.");
        }
    }

    private static List<Result> generateResults(Game game) {
        List<PlayerInGame> players = game.getPlayers();
        return players.stream()
            .map(player -> checkIfWonOrLost(player, game))
            .collect(Collectors.toList());
    }


    private static Result checkIfWonOrLost(PlayerInGame player, Game game) {
        if (player.handValue() == 21) {
            return new Result(Result.GameResult.WIN, player);
        } else if (player.isLooser()) {
            return new Result(Result.GameResult.LOSS, player);
        } else if (player.handValue() > getCroupierHandValue(game)) {
            return new Result(Result.GameResult.WIN, player);
        } else if (player.handValue() < getCroupierHandValue(game)) {
            return new Result(Result.GameResult.LOSS, player);
        } else {
            return new Result(Result.GameResult.DRAW, player);
        }
    }

    private static int getCroupierHandValue(Game game) {
        return game.getCroupier().handValue();
    }
}
