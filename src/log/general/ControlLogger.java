package log.general;

import java.util.logging.Logger;

public class ControlLogger {
	private static Logger logger;
	public static final String SERVLET_LOG_STRING = "Servlet";
	public static final String CONDITION_ELSE_UNEXPECTED = "The Condition Else in [" +
			Thread.currentThread().getStackTrace()[2].getMethodName() + "] method ocurred, but is unexpected.";
	
	private static void setUpLogger(String loggerType) {
		logger = Logger.getLogger(loggerType);
	}
	
	public static void warn(String loggerType, String warningString) {
		setUpLogger(loggerType);
		logger.warning(warningString);
	}
}
