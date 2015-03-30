package cz.packetseekers.BehaviourTrees.Exceptions;


/**
 * Custom exception for situation where node is not found in behaviour tree.
 * 
 * @author Michal Konecny
 *
 */
public class NodeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NodeNotFoundException(String message) {
		super(message);
	}

	public NodeNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
