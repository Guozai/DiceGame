package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
    private String playerId;
    private String playerName;
    private int bet;
    private int points;
    private DicePair rollResult;

    public SimplePlayer(String playerId, String playerName, int points) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public boolean placeBet(int bet) {
        if (this.points >= bet) {
            this.bet = bet;
            return true;
        } else {
            return false;
        }
    }

    public int getBet() {
        return this.bet;
    }

    public DicePair getRollResult() {
        return this.rollResult;
    }

    public void setRollResult(DicePair rollResult) {
        this.rollResult = rollResult;
    }

    public String toString() {
        return "Player: id=" + this.playerId +
                ", name =" + this.playerName + ", points=" + this.points;
    }
}
