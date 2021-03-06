package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlackjackController {

    private final Game game;

    @Autowired
    public BlackjackController(Game game) {
        this.game = game;
    }

    @PostMapping("/start-game")
    public String startGame() {
        // Game game = gameService.newGame()
        // return Game ID
        game.initialDeal();
        return redirectPage();
    }

    @PostMapping("/hit")
    public String hitCommand() {
        // requires gameId and playerId to be passed in
        // gameService.playerHitsFor(gameId, playerId)
        game.playerHits();
        return redirectPage();
    }

    @PostMapping("/stand")
    public String standCommand() {
        game.playerStands();
        return redirectPage();
    }

    // Maps Game state to Redirect (Next) Page
    private String redirectPage() {
        if (game.isPlayerDone()) {
            return "redirect:/done";
        }
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String gameView(Model model) {
        populateModelWithGameView(model);
        return "blackjack";
    }

    @GetMapping("/done")
    public String viewDone(Model model) {
        populateModelWithGameView(model);
        model.addAttribute("outcome",
                           game.determineOutcome().toString());
        return "done";
    }

    private void populateModelWithGameView(Model model) {
        GameView gameView = GameView.of(game);
        model.addAttribute("gameView", gameView);
    }
}
