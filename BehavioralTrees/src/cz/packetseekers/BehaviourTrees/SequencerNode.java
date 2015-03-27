package cz.packetseekers.BehaviourTrees;

/**
 * {@link SequencerNode} works like logical AND node. It executes children nodes
 * until one of them fail or all succeeds.
 * 
 * @author Michal Konecny
 *
 */
public class SequencerNode extends BTNode {

	/**
	 * {@inheritDoc}
	 * 
	 * {@link SequencerNode} executes every children it has until one of them
	 * fails or all are executed.
	 */
	@Override
	public Boolean execute(Object object) {
		this.changeStatus(Status.IN_PROGRESS);
		Boolean result = false;

		for (BTNode child : this.getChildren()) {
			this.changeStatus(Status.WAITING);
			result = child.execute(object);

			if (!result)
				break;
		}
		this.changeStatus(Status.DONE);
		return result;
	}
}
