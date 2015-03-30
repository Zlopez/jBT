package cz.packetseekers.bt_tester.tree.objects;

/**
 * Test object for test tree.
 * 
 * @author Michal Konecny
 *
 */
public class Door {

	private boolean locked = false;
	private boolean opened = false;
	private int strength = 50;
	private boolean blocked = false;

	public Door() {

	}

	public Door(Boolean locked, Boolean opened, int strenght, Boolean blocked) {
		this.locked = locked;
		this.opened = opened;
		this.strength = strenght;
		this.blocked = blocked;
	}

	/**
	 * Returns true if door is locked.
	 * 
	 * @return Locked status
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the locked status of {@link Door}.
	 * 
	 * @param locked
	 *            Status to set.
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Returns true if door is opened.
	 * 
	 * @return Opened status
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * Sets the opened status of {@link Door}.
	 * 
	 * @param opened
	 *            Status to set.
	 */
	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	/**
	 * Returns strength of door. Used for breaching door.
	 * 
	 * @return Strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * Sets the strength of {@link Door}.
	 * 
	 * @param strength
	 *            Value to set.
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * Returns true if door is blocked.
	 * 
	 * @return Blocked status
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * Sets the blocked status of {@link Door}.
	 * 
	 * @param blocked
	 *            Status to set.
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
