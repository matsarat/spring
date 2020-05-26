package com.trzewik.spring.domain.game;

import com.trzewik.spring.domain.player.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO probably should be moved to result domain
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultsHelper {

    static List<Result> createResults(final Game game) throws Result.Exception {
        validateGameStatus(game);
        return generateResults(game.getPlayers());
    }

    private static void validateGameStatus(final Game game) throws Result.Exception {
        if (!game.getStatus().isEnded()) {
            throw new Result.Exception("Results are available only when game finished. Please continue auction.");
        }
    }

    //TODO what if players have exactly same hand value?
    private static List<Result> generateResults(final Map<Player, PlayerInGame> players) {
        final List<Player> sorted = getSortedPlayersByResults(players);
        return IntStream.range(0, sorted.size())
            .mapToObj(index -> {
                Player player = sorted.get(index);
                return new Result(index + 1, player, players.get(player));
            })
            .collect(Collectors.toList());
    }

    private static List<Player> getSortedPlayersByResults(final Map<Player, PlayerInGame> players) {
        final List<Player> sorted = new ArrayList<>(getSortedWinners(players));
        sorted.addAll(getSortedLosers(players));
        return sorted;
    }

    private static List<Player> getSortedWinners(final Map<Player, PlayerInGame> players) {
        return players.keySet().stream()
            .filter(p -> !players.get(p).isLooser())
            .sorted(Comparator.comparing(p -> players.get(p).handValue()).reversed())
            .collect(Collectors.toList());
    }

    private static List<Player> getSortedLosers(final Map<Player, PlayerInGame> players) {
        return players.keySet().stream()
            .filter(p -> players.get(p).isLooser())
            .sorted(Comparator.comparing(p -> players.get(p).handValue()))
            .collect(Collectors.toList());
    }
}
