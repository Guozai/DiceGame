package controller;

import model.GameEngineImpl;
import model.interfaces.DicePair;
import model.interfaces.Player;
import view.GameUI;

import javax.swing.*;

public class RollHouse extends SwingWorker<Void, DicePair> {
    private final GameUI gameUI;
    private final GameEngineImpl gameEngine;
    private Player player;

    public RollHouse(GameUI gameUI, GameEngineImpl gameEngine) {
        this.gameUI = gameUI;
        this.gameEngine = gameEngine;
        this.player = gameUI.getCurrentPlayer();
        gameUI.setBalance(player);
    }

    @Override
    public Void doInBackground() {
        gameEngine.rollHouse(1, 1000, 100);
        return null;
    }

    @Override
    protected void done() {
        gameUI.setBalance(gameEngine.getPlayer(gameUI.getCurrentPlayer().getPlayerId()));
        gameUI.setStatus("   Done");
    }
}
