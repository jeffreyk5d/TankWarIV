import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;


/**
 * TankWar 
 * Good for both GUI Application & Applet
 * 
 * @author fccheng
 *
 */

public class TankWar extends Applet implements KeyListener {
	Applet a;
	public TankWar() {
		init();
		a=this;
	}

	static boolean startWarGame = false;
	public static boolean isStartWarGame() {
		return startWarGame;
	}

	public static void setStartWarGame(boolean startGame) {
		startWarGame = startGame;	
	}


	WarField warField;
	
	
	public WarField getWarField() {
		return warField;
	}

	
	// Button got all the event. How to solve this?
	Button bStart = new Button("Play");
	Button bNewGame = new Button("New Game");
	StringBuilder sb = new StringBuilder("# of Enemy Tanks: ");
	Label lbNumOfEnemyTanks = new Label(sb.toString()+10);
	Panel infoPanel = new Panel();
	public void restart() {
		this.warField.restart();
	}
	public void init() {
		
		this.setBackground(Color.GREEN);
		this.setSize(800, 600);
		this.setLayout(new BorderLayout(0, 0));
	
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStartWarGame(true);
				a.requestFocus();
			}
		});

		infoPanel.add(bStart);
		bNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restart();
				a.requestFocus();
			}
		});
		
		infoPanel.add(bNewGame);
		infoPanel.add(lbNumOfEnemyTanks);
		this.add(infoPanel, BorderLayout.SOUTH);
		
	
		warField = new WarField(0, 0, 800, 500, Color.GREEN);
		
		this.add(warField, BorderLayout.CENTER);
		

		this.setFocusable(true);
		this.addKeyListener(this);
		

		new Thread(new PaintThread()).start();
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//super.paint(g);
		this.warField.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// can not pass special keys such as VK_UP, VK_DOWN, ..
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P: // pause
			setStartWarGame(false);
			break;
		case KeyEvent.VK_S: // start
			setStartWarGame(true);
			break;
		case KeyEvent.VK_N: // New game
			restart();
			break;
		default: // control tank
			this.warField.processKeyPressed(e);
			break;
		}
		System.out.println("Tank war: press code="+e.getKeyCode()+" char="+e.getKeyChar());
		// update 
		
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("Tank war: release code="+e.getKeyCode()+" char="+e.getKeyChar());
		
	}

	public void updateWarField() {
		this.lbNumOfEnemyTanks.setText(sb.toString()+this.warField.getNumberOfEnemyTanks());
	}
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				if (startWarGame) {
					//warFieldPanel.repaint();
					updateWarField();
					repaint();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main (String[] args) {
	    // Create a frame
	    JFrame frame = new JFrame("Applet is in the frame");
	    // Create an instance of the applet
		TankWar tankWar = new TankWar();
	    // Add the applet to the frame
	    frame.getContentPane().add(tankWar);
	    // Display the frame
	    frame.setSize(800, 700);
	    frame.setLocationRelativeTo(null); // Center the frame   
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);


	}
}
