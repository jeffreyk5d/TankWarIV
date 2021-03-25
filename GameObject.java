import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;


public abstract class GameObject extends Component {
	protected int x; // location
	protected int y;
	protected int width; // size
	protected int height;
	protected Color color; // color
	
	public GameObject() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 500;
		this.height = 500;
		this.color = Color.BLACK;
	}

	public GameObject(int x, int y, int width, int height, Color color) {
		//super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getCenterX() {
		return x+width/2;
	}
	public int getCenterY() {
		return y+height/2;
	}
	abstract void draw(Graphics g) ;
	
	public boolean contain (int xp, int yp) {
		boolean flag = false;
		if (xp >= x  && xp <= x + width && yp >= y  && yp <= y + height)
			flag = true;
		return flag;
	}
}
