package javadesignpatterns.facade;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DwarvenMineWorker {
	Logger LOGGER = LoggerFactory.getLogger(DwarvenMineWorker.class);

	public void goToSleep() {
		LOGGER.info("{} goes to sleep.", name());
	}

	public void wakeUp() {
		LOGGER.info("{} wakes up.", name());
	}

	public void goHome() {
		LOGGER.info("{} goes home.", name());
	}

	public void goToMine() {
		LOGGER.info("{} goes to the mine.", name());
	}

	private void action(Action action) {
		switch (action) {
		case GO_TO_SLEEP -> goToSleep();
		case WAKE_UP -> wakeUp();
		case GO_HOME -> goHome();
		case GO_TO_MINE -> goToMine();
		case WORK -> work();
		default -> LOGGER.info("Undefined action");
		}
	}

	public void action(Action... actions) {
		Arrays.stream(actions).forEach(this::action);
	}

	public abstract void work();

	public abstract String name();

	enum Action {
		GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
	}
}