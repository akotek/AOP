package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IranPlayer implements DarkAction {
    private static Logger logger = LogManager.getLogger("stdoutLogger");
    @Override
    public void doSomeBadStuff() {
        logger.info("I'm in doSomeBadStuff for class {}", this.getClass());
    }
}
