package view;

import model.GameEngineImpl;
import model.SimplePlayer;

import javax.swing.*;
import java.awt.*;

public class DialogBox {
    private JPanel panel = new JPanel(new GridLayout(0,1));
    private JTextField fieldPlayer = new JTextField();
    private JTextField fieldBalance = new JTextField();

    public DialogBox(GameUI gameUI, GameEngineImpl gameEngine) {
        panel.add(new JLabel("Player Name:"));
        panel.add(fieldPlayer);
        panel.add(new JLabel("Balance:"));
        panel.add(fieldBalance);
        int selection = JOptionPane.showConfirmDialog(null, panel, "Create New Player",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (selection == JOptionPane.OK_OPTION) {
            SimplePlayer newPlayer;
            try {
                if (!fieldPlayer.getText().equals("")) {
                    newPlayer = new SimplePlayer(Integer.toString(gameUI.getNextPlayerID()), fieldPlayer.getText(), Integer.parseInt(fieldBalance.getText()));
                    gameEngine.addPlayer(newPlayer);
                    gameUI.comboAddItem(newPlayer);
                    gameUI.setNextPlayerID(gameUI.getNextPlayerID() + 1);
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Player name must not be empty!", "Player Name Error",
                            JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showConfirmDialog(null,
                        "Balance entered is not a number or larger than 2147483647!", "Balance Error",
                        JOptionPane.OK_CANCEL_OPTION);
            }
        } else {
            System.out.println("Cancelled");
        }
    }
}
