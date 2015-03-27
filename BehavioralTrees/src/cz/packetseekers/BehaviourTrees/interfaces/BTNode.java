package cz.packetseekers.BehaviourTrees.interfaces;

import java.util.ArrayList;

/**
 * Interface for every type of node in the tree.
 * 
 * @author Michal Konecny
 *
 */
public interface BTNode {

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
	 * Execute method for node. It must implement the way node works and returns true or false.
	 * 
	 * @param object Optional object to work with, it may contains everything {@link BTNode} needs to work with.
	 * @return succeed or failure
	 */
	public Boolean execute(Object object);

	/**
	 * Returns weight of this node. It represents the decision weight. 
	 * If there is more nodes we can choose from we always choose {@link BTNode} with highest weight.
	 * Weight is automatically adjusted every time {@link BTNode} fails or succeed. 
	 * @return weight of node
	 */
	public int getWeight();

	/**
	 * Weight is managed by tree itself, but if user wants to change it. He can do it by this method. 
	 * Default weight of each node is 0. {@link BTNode} with highest weight will be always chosen from multiple option.
	 * @param weight
	 */
	public void setWeight(int weight);

	/**
	 * Returns next {@link BTNode} that will be executed after this one. Depends on weight of node. 
	 * @return Node to be executed
	 */
	public BTNode getNextNode();

	/**
	 * Returns {@link Status} of current Node.
	 * @return {@link Status}
	 */
	public Status getStatus();
	
	/**
	 * Returns child nodes of this node.
	 * @return List of childs
	 */
	public ArrayList<BTNode> getChildrens();
	
	/**
	 * Adds new {@link BTNod}e as child.
	 * @param node Node to be add
	 */
	public void addChild(BTNode node);

	/**
	 * Removes child {@link BTNode} at specified index.
	 * @param index Index of node to be removed
	 */
	public void removeChild(int index);
	
	/**
	 * Returns parent of this {@link BTNode}.
	 * @return parent
	 */
	public BTNode getParent();
	
	/**
	 * Sets new parent of this {@link BTNode}. 
	 * @param node New parent
	 */
	public void setParent(BTNode node);
}
