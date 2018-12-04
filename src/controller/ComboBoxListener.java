package controller;

import model.interfaces.Player;
import view.GameUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxListener implements ItemListener {
    private GameUI gameUI;
    public ComboBoxListener(GameUI gameUI) {
        this.gameUI = gameUI;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            gameUI.setCurrentPlayer((Player)e.getItem());
        }
    }
}
