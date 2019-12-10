package spriteframework.sprite;

import java.util.LinkedList;

public abstract class BadSprite extends Sprite {
	public LinkedList<BadSprite>  getBadnesses() {
		return null;
	}
	public boolean isDestroyed() {
		return false;
	}
	public void act () {
		//
	}

	public String getN() {
		return N;
	}

	public void setN(String n) {
		N = n;
	}

	public String N;

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public boolean frozen;

}
