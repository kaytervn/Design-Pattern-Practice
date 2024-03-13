package javadesignpatterns.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
	public static void main(String[] args) {
		Logger LOGGER = LoggerFactory.getLogger(Messenger.class);

		var messenger = new Messenger();

		LOGGER.info("Message from the orcs: ");
		messenger.messageFromOrcs().print();

		LOGGER.info("Message from the elves: ");
		messenger.messageFromElves().print();
	}
}