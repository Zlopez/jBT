package cz.packetseekers.behaviou_trees.interfaces;

import cz.packetseekers.behaviour_trees.BTNode;
import cz.packetseekers.behaviour_trees.LeafNode;

/**
 * Interface for {@link BTNode} that needs to be executable. Only used by
 * {@link LeafNode}.
 * 
 * @author Michal Konecny
 *
 */
public interface ExecutableBTNode {
	/**
	 * Execute method for node. It must implement the way node works and set
	 * result variable. Status change of {@link BTNode} must be done manually by
	 * developer in this method. Example:
	 * 
	 * <pre>
	 * {@code 
	 * public void execute(Object object) {
	 * 	this.changeStatus(Status.IN_PROGRESS);
	 * 
	 * 	Boolean result = false;
	 * 
	 * 	this.changeStatus(Status.WAITING);
	 * 
	 * 	for (BTNode child : this.getChildren()) {
	 * 		((ExecutableBTNode) child).execute(object);
	 * 		result = !child.getResult();
	 * 	}
	 * 	this.setResult(result);
	 * 	this.changeStatus(Status.DONE);
	 * }}
	 * </pre>
	 * 
	 * @param object
	 *            Optional object to work with, it may contains everything
	 *            {@link BTNode} needs to work with.
	 */
	public void execute(Object object);
}
