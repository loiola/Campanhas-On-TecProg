package log.general;

import org.apache.log4j.Logger;

public class ControlLogger {
	private static Logger logger;
	public static final String SERVLET_LOG_STRING = "Servlet";
	public static final String CONDITION_ELSE_UNEXPECTED = "The Condition Else in ["
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
			+ "] method ocurred, but is unexpected.";
	public static final String CONDITION_IF_HANDLED = "The Condition If in ["
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
			+ "] method ocurred, and it's not acceptable, method handled the incident. You need to verify why the condition ocurred.";

	private static void setUpLogger(String loggerType) {
		logger = Logger.getLogger(loggerType);
	}

	public static void warn(String loggerType, String warningString) {
		setUpLogger(loggerType);
		logger.warn(warningString);
	}
	
	public static void debug(String loggerType, String debugString) {
		setUpLogger(loggerType);
		logger.debug(debugString);
	}
}