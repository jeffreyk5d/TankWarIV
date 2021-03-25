import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class EnemyTank extends Tank {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9198270101797245222L;
	
	public EnemyTank(WarField warField, int x, int y, int width, int height,
			Color color) {
		super(warField, x, y, width, height, color);
	}
	
	public void moveAndFire() {
		Tank t = this.warField.getOurTank();
		double randomValue = Math.random();
		if (randomValue>0.95) { // move X
			if (x > t.getX()) 	{
				this.setDirection(KeyEvent.VK_LEFT);
			} else if (x < t.getX()) {
				this.setDirection(KeyEvent.VK_RIGHT);				
			}
		} else if (randomValue<0.05) { // move Y
			if (y > t.getY()) 	{
				this.setDirection(KeyEvent.VK_UP);
			} else if (y < t.getY()) {
				this.setDirection(KeyEvent.VK_DOWN);				
			}
		} // else keep the current direction
		// fire a missile
		if (randomValue>0.999) { 
			warField.getEnemyMissiles().add(this.fire());
		}
	}
	
	void draw(Graphics g) {
		this.moveAndFire();
		this.move();
		Color orignalColor = g.getColor();
		super.drawTank(g);
		// drawMissiles(g);
		// restore color
		g.setColor(orignalColor);
	}

}
