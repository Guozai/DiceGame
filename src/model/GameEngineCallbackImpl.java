package model;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private Logger logger = Logger.getLogger("assignment1");

	public GameEngineCallbackImpl() {
		LogManager.getLogManager().reset();
		logger.setLevel(Level.ALL);

		ConsoleHandler consoleHandler = new ConsoleHandler();
		// FINE shows rolling output, INFO only shows result
		consoleHandler.setLevel(Level.FINE);
		logger.addHandler(consoleHandler);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE, player.getPlayerName() + ": ROLLING" + dicePair.toString());
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		// final results logged at Level.INFO
		logger.log(Level.INFO, player.getPlayerName() + ": *RESULT*" + result.toString());
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		// intermediate results of house
		logger.log(Level.FINE, "House: ROLLING" + dicePair.toString());
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		// final results of house logged at Level.INFO
		logger.log(Level.INFO, "House: *RESULT*" + result.toString());
		// display player results
		gameEngine.getAllPlayers().forEach(player -> logger.log(Level.INFO, player.toString()));
	}
}
