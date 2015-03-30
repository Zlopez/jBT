package cz.packetseekers.behaviour_trees;

import java.util.ArrayList;
import java.util.Collections;

import cz.packetseekers.behaviou_trees.interfaces.ExecutableBTNode;
import cz.packetseekers.behaviour_trees.utilities.Debug;

/**
 * {@link SelectorNode} works as logical OR in Behaviour Tree. It can choose
 * from multiple nodes. Priority of nodes are calculated based on their decision
 * weight. Weight is adjusted when child succeeds or fails.
 * 
 * @author Michal Konecny
 *
 */
public class SelectorNode extends BTNode implements ExecutableBTNode {

	/**
	 * {@inheritDoc}
	 * 
	 * {@link SelectorNode} chooses one of it's children by weight and executes
	 * it. This is repeated until all children are executed or one children
	 * succeeds. After execution of each children weight of this children is
	 * adjusted.
	 */
	@Override
	public void execute(Object object) {
		Debug.debug("Selector node started.");
		this.changeStatus(Status.IN_PROGRESS);

		Boolean result = false;
		ArrayList<BTNode> sortedByWeight = this.getChildren();

		Collections.sort(sortedByWeight);

		for (BTNode child : sortedByWeight) {
			this.changeStatus(Status.WAITING);
			((ExecutableBTNode) child).execute(object);
			this.changeStatus(Status.IN_PROGRESS);
			result = child.getResult();

			if (!result) {
				child.adjustWeight(false);
			} else {
				child.adjustWeight(true);
				break;
			}
		}

		this.setResult(result);
		this.changeStatus(Status.DONE);

	}
}
