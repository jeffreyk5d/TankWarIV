import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Missile extends MoveableObject {

	Tank tank; 
	/**
	 * 
	 */
	public Missile() {
		super();
		this.deltaX = 10;
		this.deltaY = 10;
		this.width = 10;
		this.height = 10;
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public Missile(int x, int y, int width, int height, Color color, Tank tank) {
		super(x, y, width, height, color);
		this.deltaX = 10;
		this.deltaY = 10;
		this.tank = tank;
		this.setDirection(tank.getDirection());
	}

	@Override
	void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (!this.isLive()) {
			// tank.getMissiles().remove(this); // cause Exception 
			return;
		}
		super.move();
		g.setColor(this.color);
		g.fillOval(x, y, width, height);
		if (x<0 || y<0 || x>1000 || y>1000) {
			super.setLive(false);
		}
	}
	public boolean hitTank (Tank t) {
		boolean hitFlag = false;
		if (t.contain(this.getCenterX(), this.getCenterY())) {
			hitFlag = true;
			t.setLive(false);
			this.setLive(false);
		}
		return hitFlag;
	}
}
