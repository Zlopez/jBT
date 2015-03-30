package cz.packetseekers.behaviour_trees;

import cz.packetseekers.behaviou_trees.interfaces.ExecutableBTNode;
import cz.packetseekers.behaviour_trees.utilities.Debug;


/**
 * {@link NegatorNode} works as logical NOT node. Reverts the result from child
 * node. It can have only one child.
 * 
 * @author Michal Konecny
 *
 */
public class NegatorNode extends BTNode implements ExecutableBTNode {

	/**
	 * {@inheritDoc}
	 * 
	 * {@link NegatorNode} only reverts result from child node.
	 */
	@Override
	public void execute(Object object) {
		Debug.debug("Negator node started.");
		this.changeStatus(Status.IN_PROGRESS);

		Boolean result = false;

		this.changeStatus(Status.WAITING);

		for (BTNode child : this.getChildren()) {
			((ExecutableBTNode) child).execute(object);
			result = !child.getResult();
		}

		this.setResult(result);
		this.changeStatus(Status.DONE);
	}

	/**
	 * {@inheritDoc} In {@link NegatorNode} this method replaces only child or
	 * adds new if there is none.
	 */
	@Override
	public void addChild(BTNode node) {
		if (this.getChildren().isEmpty()) {
			this.getChildren().add(node);
		} else {
			this.getChildren().clear();
			this.getChildren().add(node);
		}
	}

	/**
	 * {@inheritDoc} In {@link NegatorNode} this method clears children array.
	 */
	@Override
	public void removeChild(int index) {
		this.getChildren().clear();
	}
}
