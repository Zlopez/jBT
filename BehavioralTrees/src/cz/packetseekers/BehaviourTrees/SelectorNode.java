package cz.packetseekers.BehaviourTrees;

import java.util.ArrayList;
import java.util.Collections;

/**
 * {@link SelectorNode} works as logical OR in Behaviour Tree. It can choose
 * from multiple nodes. Priority of nodes are calculated based on their decision
 * weight. Weight is adjusted when child succeeds or fails.
 * 
 * @author Michal Konecny
 *
 */
public class SelectorNode extends BTNode {

	/**
	 * {@inheritDoc}
	 * 
	 * {@link SelectorNode} chooses one of it's children by weight and executes
	 * it. This is repeated until all children are executed or one children
	 * succeeds. After execution of each children weight of this children is
	 * adjusted.
	 */
	@Override
	public Boolean execute(Object object) {

		this.changeStatus(Status.IN_PROGRESS);

		Boolean result = false;
		ArrayList<BTNode> sortedByWeight = this.getChildren();

		Collections.sort(sortedByWeight);

		for (BTNode child : sortedByWeight) {
			this.changeStatus(Status.WAITING);
			result = child.execute(object);
			this.changeStatus(Status.IN_PROGRESS);

			if (!result) {
				child.adjustWeight(false);
			} else {
				child.adjustWeight(true);
				break;
			}
		}

		this.changeStatus(Status.DONE);
		return result;

	}
}
