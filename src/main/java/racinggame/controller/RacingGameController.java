package racinggame.controller;

import racinggame.exception.CarException;
import racinggame.model.Cars;
import racinggame.model.GameResult;
import racinggame.view.InputView;
import racinggame.view.OutputView;

public class RacingGameController {

	public static void playRacingGame() {
		GameResult gameResult = createGameResult(createCars());

		OutputView.printBlankLine();
		OutputView.printGameResultString();
		racingGame(gameResult);
		OutputView.printGameWinner(gameResult.getWinner());
	}

	public static void racingGame(GameResult gameResult) {
		gameResult.moveCars();
		gameResult.createWinner();
		printCarMoveResult(gameResult);
	}

	public static void printCarMoveResult(GameResult gameResult) {
		for (String result : gameResult.getMoveResultList()) {
			OutputView.printCarMoveResult(result);
		}
	}

	public static Cars createCars() {
		try {
			String carNames = InputView.inputCarNames();
			return new Cars.CarsBuilder().carList(carNames).build();
		} catch (CarException carException) {
			OutputView.printErrorMessage(carException);
			return createCars();
		}
	}

	public static GameResult createGameResult(Cars cars) {
		try {
			String gameCount = InputView.inputGameCount();
			return new GameResult.GameResultBuilder().gameCount(gameCount).cars(cars).build();
		} catch (CarException carException) {
			OutputView.printErrorMessage(carException);
			return createGameResult(cars);
		}
	}
}
