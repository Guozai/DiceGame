package controller;

import model.GameEngineImpl;
import view.GameUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private GameUI gameUI;
    private GameEngineImpl gameEngine;

    public ButtonListener(GameUI gameUI, GameEngineImpl gameEngine) {
        this.gameUI = gameUI;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton target = (JButton)e.getSource();
        String actionCommand = target.getActionCommand();
        if (actionCommand.equals("Remove Player")) {
            gameEngine.removePlayer(gameUI.getCurrentPlayer());
            gameUI.comboRemoveItem();
        }
        if (actionCommand.equals("Exit")) {
            System.exit(0);
        }
    }
}
