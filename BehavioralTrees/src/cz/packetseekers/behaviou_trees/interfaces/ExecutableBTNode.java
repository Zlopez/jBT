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
	 * result variable.
	 * 
	 * @param object
	 *            Optional object to work with, it may contains everything
	 *            {@link BTNode} needs to work with.
	 */
	public void execute(Object object);
}
