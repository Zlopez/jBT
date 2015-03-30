package cz.packetseekers.bt_tester.nodes;

import cz.packetseekers.behaviou_trees.interfaces.ExecutableBTNode;
import cz.packetseekers.behaviour_trees.LeafNode;
import cz.packetseekers.bt_tester.tree.objects.Door;

/**
 * Checks if door is locked and returns result.
 * 
 * @author Michal Konecny
 *
 */
public class CheckDoorLocked extends LeafNode implements ExecutableBTNode {

	@Override
	public void execute(Object object) {
		this.changeStatus(Status.IN_PROGRESS);

		if (object instanceof Door) {
			Door door = (Door) object;
			this.setResult(door.isLocked());
		}

		this.changeStatus(Status.DONE);
	}

}
