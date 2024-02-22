package javadesignpatterns.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DwarvenGoldDigger extends DwarvenMineWorker {

	@Override
	public void work() {
		LOGGER.info("{} digs for gold.", name());
	}

	@Override
	public String name() {
		return "Dwarf gold digger";
	}
}