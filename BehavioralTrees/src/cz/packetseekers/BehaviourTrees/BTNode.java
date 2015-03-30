package cz.packetseekers.BehaviourTrees;

import java.util.ArrayList;

/**
 * {@link BTNode} is abstract class that is inherited by all types of nodes in
 * Behaviour tree.
 * 
 * @author Michal Konecny
 *
 */
public abstract class BTNode implements Comparable<BTNode> {

	private int weight = 0;
	private BTNode parent = null;
	private Status status = Status.NOT_STARTED;
	private ArrayList<BTNode> children = new ArrayList<BTNode>();
	private Boolean result = false;

	/**
	 * Status of {@link BTNode}. 
	 * DONE - The work in this node is done.
	 * IN_PROGRESS - Node is working right now.
	 * NOT_STARTED - Work in this node is not started (default value).
	 * WAITING - Node is waiting for it child.
	 */
	public enum Status {
		DONE, IN_PROGRESS, NOT_STARTED, WAITING;
	}

	/**
	 * Execute method for node. It must implement the way node works and set
	 * result variable.
	 * 
	 * @param object
	 *            Optional object to work with, it may contains everything
	 *            {@link BTNode} needs to work with.
	 */
	public void execute(Object object) {

	}

	/**
	 * Returns weight of this node. It represents the decision weight. 
	 * If there is more nodes we can choose from we always choose {@link BTNode} with highest weight.
	 * Weight is automatically adjusted every time {@link BTNode} fails or succeed. 
	 * @return weight of node
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Weight is managed by tree itself, but if user wants to change it. He can do it by this method. 
	 * Default weight of each node is 0. {@link BTNode} with highest weight will be always chosen from multiple option.
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Returns {@link Status} of current Node.
	 * @return {@link Status}
	 */
	public synchronized Status getStatus() {
		return this.status;
	}
	
	/**
	 * Returns child nodes of this node.
	 * 
	 * @return List of children
	 */
	public ArrayList<BTNode> getChildren() {
		return this.children;
	}
	
	/**
	 * Adds new {@link BTNod}e as child.
	 * @param node Node to be add
	 */
	public void addChild(BTNode node) {
		this.getChildren().add(node);
	}

	/**
	 * Removes child {@link BTNode} at specified index.
	 * @param index Index of node to be removed
	 */
	public void removeChild(int index) {
		if (index >= 0 && index < this.getChildren().size()) {
		this.getChildren().remove(index);
		}
	}
	
	/**
	 * Returns parent of this {@link BTNode}.
	 * @return parent
	 */
	public BTNode getParent() {
		return this.parent;
	}
	
	/**
	 * Sets new parent of this {@link BTNode}. Parent can't be {@link LeafNode}.
	 * Removes this node from parent node children and adds this node as last
	 * child to new parent node.
	 * 
	 * @param node
	 *            New parent
	 */
	public void setParent(BTNode node) {
		if (!(node instanceof LeafNode)) {
			parent.removeChild(parent.getChildren().indexOf(this));
			this.parent = node;
			parent.addChild(this);
		}
	}

	/**
	 * Adjusts the weight of {@link BTNode}.
	 * 
	 * @param positive
	 *            If true weight goes up, else down.
	 */
	protected void adjustWeight(Boolean positive) {
		if (positive) {
			this.weight += 1;
		} else
			this.weight -= 1;
	}

	/**
	 * Sets new status for this {@link BTNode}
	 * 
	 * @param status
	 *            Status to set.
	 */
	public synchronized void changeStatus(Status status) {
		this.status = status;
	}

	@Override
	public int compareTo(BTNode o) {
		return this.weight - o.weight;
	}

	/**
	 * Returns result value.
	 * 
	 * @return result
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * Sets result of this Node.
	 * 
	 * @param result
	 *            Result to set
	 */
	protected void setResult(Boolean result) {
		this.result = result;
	}
}
