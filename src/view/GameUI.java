package view;

import controller.*;
import model.GameEngineCallbackGUI;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class GameUI extends JFrame {
    private final GameEngineImpl gameEngine = new GameEngineImpl();
    private GameEngineCallbackGUI gecGUI = new GameEngineCallbackGUI(this);

    private JMenuBar mb = new JMenuBar();
    private JMenu g = new JMenu("Game");
    private JMenuItem[] game = {
            new JMenuItem("Create Player", KeyEvent.VK_C),
            new JMenuItem("Exit", KeyEvent.VK_X)
    };

    private JToolBar tb = new JToolBar();
    private JLabel bidLabel = new JLabel("Bid:");
    private JTextField txtBid = new JTextField(5);
    private JButton btnRollPlayer = new JButton("Roll Player");
    private JButton btnRollHouse = new JButton("Roll House");
    private JButton btnExit = new JButton("Exit");
    private JLabel playerLabel = new JLabel("Choose Player:");
    private JComboBox<Player> comboPlayers = new JComboBox<>();
    private JButton btnRemovePlayer = new JButton("Remove Player");

    private JPanel centerPanel = new JPanel();
    private JPanel balancePanel = new JPanel();
    private JPanel playerPanel = new JPanel();
    private JPanel housePanel = new JPanel();
    private JLabel balance = new JLabel();
    private ImageIcon[] dice = {
            new ImageIcon(getClass().getResource("/images/Dice1.gif")),
            new ImageIcon(getClass().getResource("/images/Dice2.gif")),
            new ImageIcon(getClass().getResource("/images/Dice3.gif")),
            new ImageIcon(getClass().getResource("/images/Dice4.gif")),
            new ImageIcon(getClass().getResource("/images/Dice5.gif")),
            new ImageIcon(getClass().getResource("/images/Dice6.gif"))
    };
    private JLabel dicePlayer1 = new JLabel(dice[0]);
    private JLabel dicePlayer2 = new JLabel(dice[0]);
    private JLabel diceHouse1 = new JLabel(dice[0]);
    private JLabel diceHouse2 = new JLabel(dice[0]);

    private JPanel statusPanel = new JPanel();
    private JLabel status = new JLabel();

    private Player currentPlayer;
    private int nextPlayerID = 3;

    // menu listener
    private MenuListener ml = new MenuListener(this, gameEngine);
    // button listener
    private ButtonListener bl = new ButtonListener(this, gameEngine);
    // combobox state change listener
    private ComboBoxListener cl = new ComboBoxListener(this);
    // worker listener
    RollPlayerButtonListener rpl = new RollPlayerButtonListener(this, gameEngine);
    RollHouseButtonListener rhl = new RollHouseButtonListener(this, gameEngine);

    public GameUI() {
        gameEngine.addGameEngineCallback(gecGUI);

        // add pull-down menu
        for(int i = 0; i < game.length; i++) {
            game[i].addActionListener(ml);
            g.add(game[i]);
        }
        game[1].setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
        g.setMnemonic(KeyEvent.VK_G);
        mb.add(g);
        setJMenuBar(mb);

        // add toolbar
        tb.add(bidLabel);
        tb.addSeparator();
        txtBid.setMaximumSize(txtBid.getPreferredSize());
        txtBid.setText("100");
        tb.add(txtBid);
        tb.addSeparator();
        btnRollPlayer.addActionListener(rpl);
        tb.add(btnRollPlayer);
        tb.addSeparator();
        btnRollHouse.addActionListener(rhl);
        tb.add(btnRollHouse);
        tb.addSeparator();
        btnExit.addActionListener(bl);
        tb.add(btnExit);
        tb.addSeparator();
        tb.addSeparator();
        tb.addSeparator();
        tb.add(playerLabel);
        tb.addSeparator();
        // create two test players
        gameEngine.addPlayer(new SimplePlayer("1", "The Roller", 1000));
        gameEngine.addPlayer(new SimplePlayer("2", "The Loser", 500));
        for (Player player : gameEngine.getAllPlayers())
            comboPlayers.addItem(player);
        currentPlayer = (Player)comboPlayers.getSelectedItem();
        comboPlayers.addItemListener(cl);
        tb.add(comboPlayers);
        tb.addSeparator();
        btnRemovePlayer.addActionListener(bl);
        tb.add(btnRemovePlayer);
        getContentPane().add(tb, BorderLayout.NORTH);

        // add the panel which represents the rolling dice
        balancePanel.setBorder(BorderFactory.createTitledBorder("Balance"));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        housePanel.setBorder(BorderFactory.createTitledBorder("House"));
        balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.X_AXIS));
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
        housePanel.setLayout(new BoxLayout(housePanel, BoxLayout.X_AXIS));
        balancePanel.setPreferredSize(new Dimension(150, 250));
        playerPanel.setPreferredSize(new Dimension(200, 250));
        housePanel.setPreferredSize(new Dimension(200, 250));
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        balancePanel.add(balance);
        dicePlayer1.setVerticalAlignment(SwingConstants.BOTTOM);
        dicePlayer2.setVerticalAlignment(SwingConstants.BOTTOM);
        dicePlayer1.setPreferredSize(new Dimension(100,100));
        dicePlayer2.setPreferredSize(new Dimension(100,100));
        playerPanel.add(dicePlayer1);
        playerPanel.add(dicePlayer2);
        diceHouse1.setVerticalAlignment(SwingConstants.BOTTOM);
        diceHouse2.setVerticalAlignment(SwingConstants.BOTTOM);
        diceHouse1.setPreferredSize(new Dimension(100,100));
        diceHouse2.setPreferredSize(new Dimension(100,100));
        housePanel.add(diceHouse1);
        housePanel.add(diceHouse2);
        centerPanel.setLayout(new FlowLayout());
        centerPanel.add(balancePanel);
        centerPanel.add(playerPanel);
        centerPanel.add(housePanel);
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // add status bar
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        status.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(status);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);
    }
    public static void main(String[] args) { run(new GameUI(), 840, 440); }

    public static void run(final JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setTitle("Dice Game");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
            }
        });
    }

    public void setPlayerResult(DicePair dices) {
        dicePlayer1.setIcon(dice[dices.getDice1() - 1]);
        dicePlayer2.setIcon(dice[dices.getDice2() - 1]);
    }

    public void setHouseResult(DicePair dices) {
        diceHouse1.setIcon(dice[dices.getDice1() - 1]);
        diceHouse2.setIcon(dice[dices.getDice2() - 1]);
    }

    public void resetHouseResult() {
        diceHouse1.setIcon(dice[0]);
        diceHouse2.setIcon(dice[0]);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) { this.currentPlayer = player; }

    public Integer getBet() {
        try {
            return Integer.parseInt(txtBid.getText());
        } catch(NumberFormatException e) {
            JOptionPane.showConfirmDialog(null,
                    "Bid must be a number and smaller than 2147483647!", "Bid Error",
                    JOptionPane.OK_CANCEL_OPTION);
        }
        return 0;
    }

    public void setStatus(String statusString) {
        status.setText(statusString);
    }

    public void setBalance(Player player) {
        balance.setText(Integer.toString(player.getPoints()));
    }

    public int getNextPlayerID() {
        return nextPlayerID;
    }

    public void setNextPlayerID(int playerID) {
        this.nextPlayerID = playerID;
    }

    public void comboAddItem(Player player) {
        comboPlayers.addItem(player);
    }

    public void comboRemoveItem() {
        comboPlayers.removeItem(currentPlayer);
        currentPlayer = (Player)comboPlayers.getSelectedItem();
    }
}