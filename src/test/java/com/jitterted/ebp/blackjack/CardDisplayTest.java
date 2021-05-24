package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDisplayTest {
    @Test
    public void displayTenAsString() throws Exception {
        Card tenCard = new Card(Suit.CLUBS, Rank.TEN);

        String display = ConsoleCard.display(tenCard);
        assertThat(display)
                .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    public void displayNonTenAsString() throws Exception {
        Card nonTenCard = new Card(Suit.CLUBS, Rank.JACK);

        String display = ConsoleCard.display(nonTenCard);
        assertThat(display)
                .isEqualTo("[30m┌─────────┐[1B[11D│J        │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│        J│[1B[11D└─────────┘");
    }
}
