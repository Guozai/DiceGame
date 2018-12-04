package model;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.DicePair;

import java.util.*;

public class GameEngineImpl implements GameEngine {
    private Map<String, Player> players = new HashMap<>(); // a map of Player objects
    private Collection<GameEngineCallback> callbacks = new ArrayList<>();
    private Random rand = new Random(); // define rand for random number generation
    // create a DicePairImpl class instance
    private DicePairImpl dicePair = new DicePairImpl(rand.nextInt(NUM_FACES - 1 + 1) + 1, rand.nextInt(NUM_FACES - 1 + 1) + 1, NUM_FACES);

    @Override
    public boolean placeBet(Player player, int bet) {
        if (player.placeBet(bet)) { // if the player has sufficient points to place the bet
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
        // get the first member of the collection "callbacks"
        GameEngineCallback callback = callbacks.iterator().next();
        do {
            // roll the dice pair
            dicePair = new DicePairImpl(rand.nextInt(NUM_FACES - 1 + 1) + 1, rand.nextInt(NUM_FACES - 1 + 1) + 1, NUM_FACES);
            callback.intermediateResult(player, dicePair, this); // display the intermediate result
            try {
                Thread.sleep(initialDelay); // sleep for a certain time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initialDelay += delayIncrement; // increase the sleeping time by delayIncrement milliseconds
        } while (initialDelay < finalDelay);
        // if initialDelay >= finalDelay, roll the dice again, then call (final) result() method
        dicePair = new DicePairImpl(rand.nextInt(NUM_FACES - 1 + 1) + 1, rand.nextInt(NUM_FACES - 1 + 1) + 1, NUM_FACES);
        callback.result(player, dicePair, this);

        // update player dicePair parameter with the rolling result
        player.setRollResult(dicePair);
    }

    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
        GameEngineCallback callback = callbacks.iterator().next();
        do {
            dicePair = new DicePairImpl(rand.nextInt(NUM_FACES - 1 + 1) + 1, rand.nextInt(NUM_FACES - 1 + 1) + 1, NUM_FACES);
            callback.intermediateHouseResult(dicePair, this);
            try {
                Thread.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initialDelay += delayIncrement;
        } while (initialDelay < finalDelay);

        dicePair = new DicePairImpl(rand.nextInt(NUM_FACES - 1 + 1) + 1, rand.nextInt(NUM_FACES - 1 + 1) + 1, NUM_FACES);
        gameResult(dicePair);
        callback.houseResult(dicePair, this);
    }

    // calculate and store the new points after winning, losing, or draw
    private void gameResult(DicePair houseResult) {
        for (Player player : players.values()) {
            if (player.getRollResult().getDice1() + player.getRollResult().getDice2()
                    > houseResult.getDice1() + houseResult.getDice2()) {
                // player win
                player.setPoints(player.getPoints() + player.getBet());
            } else if (player.getRollResult().getDice1() + player.getRollResult().getDice2()
                    < houseResult.getDice1() + houseResult.getDice2()){
                // player lose
                player.setPoints(player.getPoints() - player.getBet());
            }
            // if draw, the points is unchanged (bet is given back to the player)
        }
    }

    @Override
    public void addPlayer(Player player){
        players.put(player.getPlayerId(), player);
    }

    @Override
    public Player getPlayer(String id) {
        return players.get(id);
    }

    @Override
    public boolean removePlayer(Player player) {
        if (players.containsKey(player.getPlayerId())) {
            players.remove(player.getPlayerId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        callbacks.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        if (callbacks.iterator().hasNext()) {
            if (callbacks.iterator().next().equals(gameEngineCallback))
                callbacks.remove(gameEngineCallback);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return this.players.values();
    }
}
