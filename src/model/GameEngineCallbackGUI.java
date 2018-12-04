package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.GameUI;

import javax.swing.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private Logger logger = Logger.getLogger("assignment2");
    private final GameUI gameUI;

//    public Player player;
    public GameEngineCallbackGUI(GameUI gameUI) {
        this.gameUI = gameUI;

        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // FINE shows rolling output, INFO only shows result
        consoleHandler.setLevel(Level.FINE);
        logger.addHandler(consoleHandler);
    }

    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
        if (player.getPlayerId() == gameUI.getCurrentPlayer().getPlayerId()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gameUI.setPlayerResult(dicePair);
                    gameUI.setStatus("   Rolling...");
                }
            });
        } else
            logger.log(Level.FINE, player.getPlayerName() + ": ROLLING" + dicePair.toString());
    }

    public void result(Player player, DicePair result, GameEngine gameEngine) {
        if (player.getPlayerId() == gameUI.getCurrentPlayer().getPlayerId()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gameUI.setPlayerResult(result);
                }
            });
        } else
            logger.log(Level.INFO, player.getPlayerName() + ": *RESULT*" + result.toString());
    }

    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameUI.setHouseResult(dicePair);
                gameUI.setStatus("   Rolling...");
            }
        });
    }

    public void houseResult(DicePair result, GameEngine gameEngine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameUI.setHouseResult(result);
                System.out.println("Arrive Here.");
            }
        });
        gameEngine.getAllPlayers().forEach(player -> logger.log(Level.INFO, player.toString()));
    }
}
