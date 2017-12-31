package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.codecool.enterprise.overcomplicated.service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"player", "game"})
public class GameController {

    @Autowired
    ServiceHandler serviceHandler;

    @ModelAttribute("player")
    public Player getPlayer() {
        return new Player();
    }

    @ModelAttribute("game")
    public TictactoeGame getGame() {
        return new TictactoeGame();
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarUri(@ModelAttribute Player player) {
        return serviceHandler.getAvatar(player.getUserName());
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player,
                           @ModelAttribute TictactoeGame game,
                           Model model) {

        if (game.isOver()) {
            model.addAttribute("endgamemsg", game.getEndGameMsg());
        }
        model.addAttribute("funfact", serviceHandler.getFunfact());
        model.addAttribute("comic_uri", serviceHandler.getComic());
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player,
                           @ModelAttribute("move") int move,
                           @ModelAttribute("game") TictactoeGame game) {

        if (game.getGameState()[move] == '-') {
            game.movePlayer(move);
            System.out.println("Player moved " + move);

            int aiMoveRecommendation = serviceHandler.getAIMove(game.getGameState());

            game.moveAI(aiMoveRecommendation);

            System.out.println("Gamestate: " + new String(game.getGameState()));
        }
        return "redirect:/game";
    }

    @GetMapping(value = "/restart-game")
    public String restartGame(Model model) {
        model.addAttribute("game", new TictactoeGame());
        return "redirect:/game";
    }
}
