import java.awt.Color;
import java.awt.event.KeyEvent;


public abstract class MoveableObject extends GameObject {

	protected boolean live = true; // isLive
	protected int deltaX;	  // SPEED
	protected int deltaY;
	//public static enum Direction {UP, RIGHT, DOWN, LEFT}
	protected int strength; 	  // life value
	protected int direction=KeyEvent.VK_RIGHT;
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	/**
	 * 
	 */
	public MoveableObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public MoveableObject(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getDeltaX() {
		return deltaX;
	}
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	};

	public void move() {
		switch (this.direction) {
		case KeyEvent.VK_UP:
			y-=deltaY;
			break;
		case KeyEvent.VK_RIGHT:
			x+=deltaX;
			break;
		case KeyEvent.VK_DOWN:
			y+=deltaY;
			break;
		case KeyEvent.VK_LEFT:
			x-=deltaX;
			break;

		default:
			break;
		}
	}
	
/*	public void move(int dir) {
		//this.direction = dir;
		switch (dir) {
		case KeyEvent.VK_UP:
			y-=deltaY;
			break;
		case KeyEvent.VK_RIGHT:
			x+=deltaX;
			break;
		case KeyEvent.VK_DOWN:
			y+=deltaY;
			break;
		case KeyEvent.VK_LEFT:
			x-=deltaX;
			break;

		default:
			break;
		}
	}*/
	
	public boolean  collidedWith (GameObject go) { //¬Û¼²°»´ú

		if (this.contain(go.x, go.y) || 
			 this.contain(go.x, go.y+go.getHeight()) ||
			 this.contain(go.x+go.getWidth(), go.y) ||
			 this.contain(go.x+go.getWidth(), go.y+go.getHeight())) {
			return true;
		}

		return false;
	}
	
}
