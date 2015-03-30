package cz.packetseekers.bt_tester.nodes;

import cz.packetseekers.behaviou_trees.interfaces.ExecutableBTNode;
import cz.packetseekers.behaviour_trees.LeafNode;
import cz.packetseekers.bt_tester.tree.objects.Door;

/**
 * Node for opening door.
 * 
 * @author Michal Konecny
 *
 */
public class OpenDoorNode extends LeafNode implements ExecutableBTNode {

	@Override
	public void execute(Object object) {
		this.changeStatus(Status.IN_PROGRESS);

		if (object instanceof Door) {
			Door door = (Door) object;
			door.setOpened(true);
			this.setResult(true);
		}

		this.changeStatus(Status.DONE);
	}

}
