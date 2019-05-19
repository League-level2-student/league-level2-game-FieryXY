package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	//Timers
	Timer frameDraw;
	Timer roadSpawn;
	
	//The States
	public final int MENU = 0;
	public final int ENDLESS = 1;
	public final int TUTORIAL = 2;
	public final int SHOP = 3;
	
	//Object Managers
	EndlessObjectManager eom;
	
	//Color Integers
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int MAGENTA = 3;
	
	public int currentState = MENU;
	
	GamePanel() {
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MENU) {
			drawMenuState(g);
		}
		else if(currentState == ENDLESS) {
			drawEndlessState(g);
		}
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ColorDash.WIDTH, ColorDash.HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(200, 100, (ColorDash.WIDTH/2)-100, 30);
	}
	void updateMenuState() {
		
	}
	void drawEndlessState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ColorDash.WIDTH, ColorDash.HEIGHT);
		eom.draw(g);
	}
	void updateEndlessState() {
		eom.update();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(currentState == MENU) {
			if(e.getKeyCode() == KeyEvent.VK_E) {
				System.out.println("hi");
				currentState = ENDLESS;
				 eom = new EndlessObjectManager(new Soldier(ColorDash.WIDTH/2-30, ColorDash.HEIGHT-120, Color.RED));
				 startEndless();
			}
		}
		else if(currentState == ENDLESS) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				eom.player.left();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				eom.player.right();
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				if(eom.isScrolling == false) {
					eom.isScrolling = true;
				}
				else {
					eom.isScrolling = false;
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == MENU) {
			updateMenuState();
		}
		else if(currentState == ENDLESS) {
			updateEndlessState();
		}
		this.repaint();
	}

	void startEndless() {
		 roadSpawn = new Timer(4500, eom);
		roadSpawn.start();
	}
	
}
