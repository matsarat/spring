package com.trzewik.spring.infrastructure.db.game;

import com.trzewik.spring.domain.game.Card;
import com.trzewik.spring.domain.game.Rank;
import com.trzewik.spring.domain.game.Suit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardDto {
    private Suit suit;
    private Rank rank;

    public static CardDto from(Card card) {
        return new CardDto(
            card.getSuit(),
            card.getRank()
        );
    }

    public static Card to(CardDto dto) {
        return new Card(dto.suit, dto.rank);
    }
}
