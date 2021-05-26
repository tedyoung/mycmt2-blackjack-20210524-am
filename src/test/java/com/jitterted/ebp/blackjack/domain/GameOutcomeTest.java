package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void playerStandsAndPlayerIsDone() throws Exception {
        Game game = new Game();
        game.initialDeal();

        game.playerStands();

        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    public void playerBeatsDealer() throws Exception {
        Game game = new Game(StubDeck.createPlayerStandsAndBeatsDealerDeck());
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void playerGoesBustAndOutcomeReturnsPlayerBusted() throws Exception {
        Game game = new Game(StubDeck.createPlayerHitsGoesBustDeck());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void playerDealtAceAndTenValueCardResultsInWinningBlackjack() throws Exception {
        Deck playerDealtBlackjack = new StubDeck(Rank.QUEEN, Rank.EIGHT,
                                                 Rank.ACE,   Rank.JACK);
        Game game = new Game(playerDealtBlackjack);
        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isTrue();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }
}