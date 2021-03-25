import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;



/**
 * TankWar 
 * Good for both GUI Application & Applet
 * 
 * @author fccheng
 *
 */
public class Tank extends MoveableObject {
	WarField warField;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	
	public Tank(WarField warField, int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
		// set speed
		this.warField=warField;
		this.setDeltaX(5);
		this.setDeltaY(5);
	}


	
	public WarField getWarField() {
		return warField;
	}



	public void setWarField(WarField warField) {
		this.warField = warField;
	}




	/* (non-Javadoc)
	 * @see GameObject#draw()
	 */
	@Override
	void draw(Graphics g) {
		// TODO Auto-generated method stub
		Color orignalColor = g.getColor();
		drawTank(g);
		// drawMissiles(g);
		// restore color
		g.setColor(orignalColor);
	}



/*	private void drawMissiles(Graphics g) {
		// TODO Auto-generated method stub
		for (Missile m: this.missiles) {
			m.draw(g);
		}
	}*/



	void drawTank(Graphics g) {
		// draw Tank's Base
		drawBase(g);
		// draw Gun and tracks
		switch (this.getDirection()) {
		case KeyEvent.VK_UP:
			this.drawGunMoveUp(g);
			this.drawTracksUpDown(g);
			break;
		case KeyEvent.VK_RIGHT:
			this.drawGunMoveRight(g);
			this.drawTracksLeftRight(g);
			break;
		case KeyEvent.VK_DOWN:
			this.drawGunMoveDown(g);
			this.drawTracksUpDown(g);
			break;
		case KeyEvent.VK_LEFT:
			this.drawGunMoveLeft(g);
			this.drawTracksLeftRight(g);
			break;
			
		default:
			break;
		}
	}



	private void drawBase(Graphics g) {
		// draw base
		g.setColor(this.getColor());
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.MAGENTA);
		g.fillOval(x+8, y+8, 14, 14);
	}



	private void drawGunMoveRight(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getCenterX(), getCenterY(), x+width, getCenterY());

	}
	
	private void drawGunMoveLeft(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getCenterX(), getCenterY(), x, getCenterY());

	}
	
	private void drawTracksLeftRight(Graphics g) {
		g.setColor(Color.GRAY);
		for (int i=0;i<=5;i++) {
			g.drawRect(x+5*i, y, 5, 5);
		}
		for (int i=0;i<=5;i++) {
			g.drawRect(x+5*i, y+height-5, 5, 5);
		}
	}
	
	private void drawGunMoveUp(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getCenterX(), getCenterY(), getCenterX(), y);

	}
	private void drawGunMoveDown(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getCenterX(), getCenterY(), getCenterX(), y+height);

	}
	
	private void drawTracksUpDown(Graphics g) {
		g.setColor(Color.GRAY);
		for (int i=0;i<=5;i++) {
			g.drawRect(x, y+5*i, 5, 5);
		}
		for (int i=0;i<=5;i++) {
			g.drawRect(x+width-5, y+5*i, 5, 5);
		}
	}

	public Missile fire() {
		return fire (Color.BLACK);
	}

	public Missile fire(Color color) {
		Missile missile = new Missile(this.getCenterX()-5 ,
				this.getCenterY()-5, 10, 10, color, this);
		missile.setDirection(this.getDirection());
		return missile;

	}

/*	public void move(int dir) {
		this.setDirection(dir);
		super.move();
	}*/
	private int[] allDir = {KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT};
	
	public void processKeyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_SPACE: 
			this.warField.getOurMissiles().add(this.fire(Color.RED));
			break;
		case KeyEvent.VK_A: 
			Missile m;
			for (int i=0;i<4;i++) {
				m = this.fire(Color.RED);
				m.setDirection(allDir[i]);
				this.warField.getOurMissiles().add(m);
			}
			break;
		case KeyEvent.VK_UP:
			this.setDirection(KeyEvent.VK_UP);
			break;
		case KeyEvent.VK_RIGHT:
			this.setDirection(KeyEvent.VK_RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			this.setDirection(KeyEvent.VK_DOWN);
			break;
		case KeyEvent.VK_LEFT:
			this.setDirection(KeyEvent.VK_LEFT);
			break;

		default:
			
			break;
		}
		super.move();
	}




}
