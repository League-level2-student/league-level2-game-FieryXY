package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Soldier extends ColoredGameObject{
	

	int speed = 20;
	
	
	Soldier(int x, int y, Color color) {
		super(x,y,color);
		
	}
	
	void draw(Graphics g) {
		g.setColor(super.color);
		g.fillRect(super.x, super.y, 30, 30);
	}
	
	void up() {
		this.y -= speed;
	}
	void down() {
		this.y += speed;
	}
	void right() {
		this.x += speed;
	}
	void left() {
		this.x -= speed;
	}

}
