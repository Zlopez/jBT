package cz.packetseekers.BehaviourTrees;

import java.util.ArrayList;

import cz.packetseekers.BehaviourTrees.BTNode.Status;
import cz.packetseekers.BehaviourTrees.Exceptions.NodeNotFoundException;

/**
 * This class represents tree itself. It manages the run of behaviour tree and
 * provides methods for modifying the tree.
 * 
 * @author Michal Konecny
 *
 */
public class BehaviourTree {

	private BTNode root = null;
	private ArrayList<BTNode> nodes = new ArrayList<BTNode>();

	/**
	 * Creates new {@link BehaviourTree} with root {@link BTNode}. Tree is then
	 * looking if this root has any children and adds them to tree.
	 * 
	 * @param root
	 *            {@link BTNode} to add as root
	 */
	public BehaviourTree(BTNode root) {
		this.root = root;
		nodes.add(root);
		initTree();
	}

	/**
	 * This method adds new child to specified parent {@link BTNode}.
	 * 
	 * @param node
	 *            {@link BTNode} to add
	 * @param parent
	 *            {@link BTNode} parent of new child
	 * 
	 * @throws NodeNotFoundException
	 *             When parent is not found in tree
	 */
	public void addChild(BTNode node, BTNode parent)
			throws NodeNotFoundException
	{
		if(this.nodes.contains(parent))
		{
			this.nodes.removeAll(parent.getChildren());
			parent.addChild(node);
			this.nodes.addAll(parent.getChildren());
		} else {
			throw new NodeNotFoundException("Node not found in tree.");
		}
	}

	/**
	 * This methods adds new children to specified parent {@link BTNode}.
	 * Warning: If the node can't have multiple children only last one in
	 * {@link ArrayList} is added.
	 * 
	 * @param nodes
	 *            {@link ArrayList} of {@link BTNode}s that will be added as
	 *            children to parent.
	 * @param parent
	 *            {@link BTNode} parent of new child
	 * @throws NodeNotFoundException
	 *             When parent is not found in tree
	 */
	public void addChildren(ArrayList<BTNode> nodes, BTNode parent)
			throws NodeNotFoundException {
		if (this.nodes.contains(parent)) {
			this.nodes.removeAll(parent.getChildren());

			for (BTNode child : nodes) {
				parent.addChild(child);
			}

			this.nodes.addAll(nodes);
		} else
			throw new NodeNotFoundException("Node not found in tree.");
	}

	/**
	 * Returns root of {@link BehaviourTree}.
	 * 
	 * @return Root {@link BTNode}
	 */
	public BTNode getRoot() {
		return this.root;
	}

	/**
	 * Sets new root {@link BTNode} for {@link BehaviourTree}. New
	 * initialisation of tree is done. All nodes are recalculated.
	 * 
	 * @param node
	 *            {@link BTNode} to be set as new root
	 */
	public void setRoot(BTNode node) {
		this.root = node;
		initTree();
	}
	
	/**
	 * This method search all nodes in tree for children and adds them as nodes.
	 * All nodes in tree are first removed except root. This method is called
	 * when structure of tree is changed.
	 */
	private void initTree() {
		this.nodes.clear();
		this.nodes.addAll(getAllChildren(root, new ArrayList<BTNode>()));
	}

	/**
	 * Recursive method that searches all {@link BTNode}s in
	 * {@link BehaviourTree} and returns there children as {@link ArrayList}.
	 * 
	 * @param root
	 *            Root {@link BTNode} where search begin
	 * @param nodes
	 *            This variable contains found nodes so far
	 * @return All found children
	 */
	private ArrayList<BTNode> getAllChildren(BTNode root,
			ArrayList<BTNode> nodes) {
		nodes.addAll(root.getChildren());
		for (BTNode child : root.getChildren()) {
			nodes.addAll(getAllChildren(child, nodes));
		}
		return nodes;
	}

	/**
	 * Removes node from tree. Tree is then reinitialised.
	 * 
	 * @param node
	 *            {@link BTNode} to remove
	 * @throws NodeNotFoundException
	 *             When node is not found in tree
	 */
	public void removeNode(BTNode node) throws NodeNotFoundException {
		if (this.nodes.contains(node)) {
			node.getParent().removeChild(
					node.getParent().getChildren().indexOf(node));
			initTree();
		} else
			throw new NodeNotFoundException("Node not found in tree.");
	}

	public Boolean execute(Object object) {
		Thread treeThread = new Thread(new Runnable() {

			@Override
			public void run() {
				root.execute(object);
			}
		});

		treeThread.start();

		while (root.getStatus() != Status.DONE) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return root.getResult();
	}
}
