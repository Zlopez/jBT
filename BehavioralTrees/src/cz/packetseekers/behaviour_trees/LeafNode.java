package cz.packetseekers.behaviour_trees;

import java.util.ArrayList;

import cz.packetseekers.behaviou_trees.interfaces.ExecutableBTNode;

/**
 * Leaf node in {@link BehaviourTree}. It represents the action node, that will
 * creates result for situation. This is abstract class and must be extended to
 * provide needed functionality. Classes that extends this class must implement
 * {@link ExecutableBTNode}
 * 
 * @author Michal Konecny
 *
 */
public abstract class LeafNode extends BTNode {

	/**
	 * {@inheritDoc}
	 * 
	 * For {@link LeafNode} this always returns null.
	 */
	@Override
	public ArrayList<BTNode> getChildren() {
		return new ArrayList<BTNode>();
	}

	/**
	 * {@inheritDoc} This method does nothing in {@link LeafNode}.
	 */
	@Override
	public void addChild(BTNode node) {
	}

	/**
	 * {@inheritDoc} This method does nothing in {@link LeafNode}.
	 */
	@Override
	public void removeChild(int index) {
	}

}
