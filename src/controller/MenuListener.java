package controller;

import model.GameEngineImpl;
import view.DialogBox;
import view.GameUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener{
    private GameUI gameUI;
    private GameEngineImpl gameEngine;
    public MenuListener(GameUI gameUI, GameEngineImpl gameEngine) {
        this.gameUI = gameUI;
        this.gameEngine = gameEngine;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem target = (JMenuItem)e.getSource();
        String actionCommand = target.getActionCommand();
        if (actionCommand.equals("Create Player")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new DialogBox(gameUI, gameEngine);
                }
            });
        }
        if (actionCommand.equals("Exit")) {
            System.exit(0);
        }
    }
}
