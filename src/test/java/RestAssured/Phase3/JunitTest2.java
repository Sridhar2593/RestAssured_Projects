package RestAssured.Phase3;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

public class JunitTest2 {
	
final Logger LOGGER = LogManager.getLogger(JunitTest2.class.getSimpleName());
	
	@Before
	public void before() {
		
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@Test
	public void Test1() {
		
		LOGGER.info("Log4j Sample test");
		LOGGER.debug("Log4j Sample Debug");
		LOGGER.warn("Log4j Sample Warning");
		LOGGER.error("Log4j Sample Error");
		LOGGER.fatal("Log4j Sample Fatal");
	}

}
