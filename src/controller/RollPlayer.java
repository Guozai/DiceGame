package controller;

import model.GameEngineImpl;
import model.interfaces.DicePair;
import model.interfaces.Player;
import view.GameUI;

import javax.swing.*;

public class RollPlayer extends SwingWorker<Void, DicePair> {
    private final GameUI gameUI;
    private final GameEngineImpl gameEngine;
    private Player player;
    private Integer bet;

    public RollPlayer(GameUI gameUI, GameEngineImpl gameEngine) {
        this.gameUI = gameUI;
        this.gameEngine = gameEngine;
        this.player = gameUI.getCurrentPlayer();
        this.bet = gameUI.getBet();
        gameUI.setBalance(player);
        gameUI.resetHouseResult();
    }

    @Override
    public Void doInBackground() {
        if (bet != 0) {
            gameEngine.placeBet(player, bet);
            gameEngine.rollPlayer(player, 1, 1000, 100);
        }
        return null;
    }

    @Override
    protected void done() {
        gameUI.setStatus("   Done");
    }
}
