package com.trzewik.spring.domain.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@Getter
@EqualsAndHashCode
public class Result {
    private final GameResult gameResult;
    private final @NonNull String name;
    private final @NonNull Set<Card> hand;
    private final int handValue;

    Result(final GameResult gameResult, @NonNull final PlayerInGame playerInGame) {
        this.gameResult = gameResult;
        this.name = playerInGame.getName();
        this.hand = playerInGame.getHand();
        this.handValue = playerInGame.handValue();
    }

    public static class Exception extends java.lang.Exception {
        public Exception(String msg) {
            super(msg);
        }
    }

    public enum GameResult {
        WIN,
        LOSS,
        DRAW
    }
}
