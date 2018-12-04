package controller;

import model.GameEngineImpl;
import view.GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollPlayerButtonListener implements ActionListener {
    private RollPlayer task;
    private GameUI gameUI;
    private GameEngineImpl gameEngine;

    public RollPlayerButtonListener(GameUI gameUI, GameEngineImpl gameEngine) {
        this.gameUI = gameUI;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        task = new RollPlayer(gameUI, gameEngine);
//        task.addPropertyChangeListener(new PropertyChangeListener() {
//            public void propertyChange(PropertyChangeEvent evt) {
//                if ("progress".equals(evt.getPropertyName()) && evt.getNewValue() != null)
//                    gameUI.setProgress((Integer) evt.getNewValue());
//            }
//        });
        task.execute();
    }
}
