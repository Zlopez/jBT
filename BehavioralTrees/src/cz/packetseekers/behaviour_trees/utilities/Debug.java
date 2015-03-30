package cz.packetseekers.behaviour_trees.utilities;

/**
 * Class for debug purpose. It prints class name and line number in debug text.
 * For showing debug outputs variable debug must be set true.
 * 
 * @author Michal Konecny
 *
 */
public class Debug {

	public static boolean debug = false;

	public static void debug(String message) {
		if (debug) {
			String fullClassName = Thread.currentThread().getStackTrace()[2]
					.getClassName();
			String className = fullClassName.substring(fullClassName
					.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[2]
					.getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[2]
					.getLineNumber();

			System.out.println(className + "." + methodName + "():"
					+ lineNumber + " \"" + message + "\"");
		}
	}
}
