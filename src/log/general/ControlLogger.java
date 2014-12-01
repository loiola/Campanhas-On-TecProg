package log.general;

import java.util.logging.Logger;

public class ControlLogger {
	private static Logger logger;

	public static final String SERVLET_LOG_STRING = "Servlet";

	public static final String INFORM_BEGIN_CALLED_METHOD = "The Method ["
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
			+ "] is now running.\nFrom Object ["
			+ Thread.currentThread().getStackTrace()[2].getClassName() + "]";
	
	public static final String INFORM_END_CALLED_METHOD = "The Method ["
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
			+ "] reached the end.\nFrom Object ["
			+ Thread.currentThread().getStackTrace()[2].getClassName() + "].";

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
		logger.warning(warningString);
	}

	public static void info(String loggerType, String infoString) {
		setUpLogger(loggerType);
		logger.info(infoString);
	}
	
	public static String returnInformations(Object returnedVariable) {
		String returnInformations = "\nThe returned parameter has the type ["
				+ returnedVariable.getClass() + "] with value ["
				+ returnedVariable + "].";
		return returnInformations;
	}
}