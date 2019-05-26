package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Soldier extends ColoredGameObject{
	

	int speed = 30;
	Rectangle collisionBox = new Rectangle();
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Soldier(int x, int y) {
		super(x,y);
		loadImage();
	}
	
	void loadImage() {
		if(needImage) {
			if(super.color == Color.RED) {
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("PlayerRed.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			else if(super.color == Color.BLUE) {
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("PlayerBlue.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			else if(super.color == Color.GREEN) {
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("PlayerGreen.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			else if(super.color == Color.MAGENTA) {
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("PlayerMagenta.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			needImage = false;
		}
	}
	
	void draw(Graphics g) {
		if(gotImage == true) {
			//Load Image
			g.drawImage(image, super.x, super.y, 30, 30, null);
		}
		else {
			//Draw Rectangle
			g.setColor(super.color);
			g.fillRect(super.x, super.y, 30, 30);
		}
		
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
