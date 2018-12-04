package controller;

import model.GameEngineImpl;
import view.GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollHouseButtonListener implements ActionListener {
    private RollHouse task;
    private GameUI gameUI;
    private GameEngineImpl gameEngine;

    public RollHouseButtonListener(GameUI gameUI, GameEngineImpl gameEngine) {
        this.gameUI = gameUI;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        task = new RollHouse(gameUI, gameEngine);
        task.execute();
    }
}
