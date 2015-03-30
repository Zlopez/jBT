package cz.packetseekers.bt_tester;

import cz.packetseekers.behaviour_trees.BehaviourTree;
import cz.packetseekers.behaviour_trees.NegatorNode;
import cz.packetseekers.behaviour_trees.SequencerNode;
import cz.packetseekers.behaviour_trees.utilities.Debug;
import cz.packetseekers.bt_tester.nodes.CheckDoorLocked;
import cz.packetseekers.bt_tester.nodes.OpenDoorNode;
import cz.packetseekers.bt_tester.tree.objects.Door;

public class Main {

	public static void main(String[] args) {
		Debug.debug = true;
		BehaviourTree tree = initTree();
		Door door = new Door(false, false, 50, false);
		boolean result = false;

		result = tree.execute(door);

		if (result) {
			System.out.println("Door was successfully opened.");
		} else
			System.out.println("Door is still closed.");
	}

	private static BehaviourTree initTree() {
		SequencerNode root = new SequencerNode();
		NegatorNode not = new NegatorNode();
		CheckDoorLocked checkDoor = new CheckDoorLocked();
		OpenDoorNode openDoor = new OpenDoorNode();

		root.addChild(not);
		root.addChild(openDoor);
		not.addChild(checkDoor);

		BehaviourTree tree = new BehaviourTree(root);

		return tree;
	}

}
