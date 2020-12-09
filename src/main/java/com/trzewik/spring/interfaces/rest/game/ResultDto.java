package com.trzewik.spring.interfaces.rest.game;

import com.trzewik.spring.domain.game.Result;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultDto {
    private final Result.GameResult gameResult;
    private final String name;
    private final HandDto hand;

    public static ResultDto from(final Result result) {
        return new ResultDto(
            result.getGameResult(),
            result.getName(),
            HandDto.from(result.getHand(), result.getHandValue())
        );
    }
}
