package com.fieryxy;

import javax.swing.JFrame;

public class ColorDash {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	JFrame frame;
	GamePanel gPanel;
	
	ColorDash(JFrame frame, GamePanel gPanel) {
		this.frame = frame;
		this.gPanel = gPanel;
	}
	
	public static void main(String[] args) {
		ColorDash colorDash = new ColorDash(new JFrame(), new GamePanel());
		colorDash.setup();
	}
	
	void setup() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(gPanel);
		frame.addKeyListener(gPanel);
		frame.setVisible(true);
	}
	
}
