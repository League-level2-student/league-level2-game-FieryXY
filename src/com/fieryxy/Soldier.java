package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Soldier extends ColoredGameObject{
	

	int speed = 30;
	Rectangle collisionBox = new Rectangle();
	
	
	Soldier(int x, int y) {
		super(x,y);
	}
	
	void draw(Graphics g) {
		g.setColor(super.color);
		g.fillRect(super.x, super.y, 30, 30);
	}
	
	void update() {
		collisionBox.setBounds(x, y, 30, 30);
	}
	
	void up() {
		this.y -= speed;
	}
	void down() {
		this.y += speed;
	}
	void right() {
		if(this.x+30 < ColorDash.WIDTH-10) {
			this.x += speed;
		}
		
	}
	void left() {
		if(this.x > 10) {
			this.x -= speed;
		}
		
	}

}
