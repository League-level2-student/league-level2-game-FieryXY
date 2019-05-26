package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import com.fieryxy.Obstacle.Direction;
import com.fieryxy.Obstacle.ObstacleType;

public class Street extends RegularGameObject {
	
	int roadVelocity = 3;
	int streetY;
	
	
	int laneCount;
	EndlessObjectManager manager;
	ArrayList<LaneTraffic> laneArr = new ArrayList<LaneTraffic>();
	Random streetRand = new Random();

	//boolean isLarge;
	public enum StreetType {
		ONE_WAY, TWO_WAY;
	}
	
	StreetType streetType;
	
	Street(int x, int y, int laneCount, StreetType streetType, EndlessObjectManager manager) {
		super(x,y);
		this.laneCount = laneCount;
		this.manager = manager;
		this.streetType = streetType;
		streetY = y;
		createLanes();
	}
	
	void createLanes() {
		int someY = 10;
		for(int k = 0; k < laneCount; k++) {
			laneArr.add(new LaneTraffic((streetRand.nextInt(1)+1)*-1, someY));
			laneArr.get(laneArr.size()-1).start();
			someY += 50;
		}
		if(streetType == StreetType.TWO_WAY) {
			for(int k = 0; k < laneCount; k++) {
				laneArr.add(new LaneTraffic((streetRand.nextInt(1)+2), someY));
				laneArr.get(laneArr.size()-1).start();
				someY += 50;
			}
		}
	}
	
	void update() {
		if(manager.isScrolling == true) {
			super.y += roadVelocity;
			streetY += roadVelocity;
		}
		for(LaneTraffic l : laneArr) {
			l.update();
		}
		
	}
	
	
	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		if(streetType == StreetType.ONE_WAY) {
			g.fillRect(super.x-1, super.y, ColorDash.WIDTH+1, 50*laneCount);
		}
		else if(streetType == StreetType.TWO_WAY) {
			g.fillRect(super.x-1, super.y, ColorDash.WIDTH+1, 100*laneCount);
		}
		
		for(LaneTraffic l : laneArr) {
			l.drawLane(g);
		}
		
	}

	
	
	Color chooseRandomColor() {
		Random randomColor = new Random();
		int colorInt = randomColor.nextInt(4);
		if(colorInt == 0) {
			return Color.RED;
		}
		else if(colorInt == 1) {
			return Color.BLUE;
		}
		else if(colorInt == 2) {
			return Color.GREEN;
		}
		else if(colorInt == 3) {
			return Color.MAGENTA;
		}
		else {
			return Color.GRAY;
		}
	}
	public class LaneTraffic {
		int speed;
		int lanePositionY;
		
		int obstacleDistance;
		
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		
		
		
		LaneTraffic(int speed, int lanePositionY) {
			this.speed = speed;
			this.lanePositionY = lanePositionY;
			this.obstacleDistance = obstacleDistance;
			if(Street.this.streetType == StreetType.TWO_WAY) {
				obstacleDistance = 300;
			}
			else {
				obstacleDistance = 200;
			}
		}
		void start() {
			int nextX = 0;
			while(nextX <= ColorDash.WIDTH) {
				if(speed > 0) {
					obstacles.add(new Obstacle(nextX, Street.this.streetY + lanePositionY, chooseObstacleType(), Direction.RIGHT, chooseRandomColor()));
					obstacleDebug(obstacles.get(obstacles.size()-1), "start");
					
			}
			else if(speed < 0) {	
					obstacles.add(new Obstacle(nextX, Street.this.streetY + lanePositionY, chooseObstacleType(), Direction.LEFT, chooseRandomColor()));
					obstacleDebug(obstacles.get(obstacles.size()-1), "start");
					
			}
			nextX += obstacleDistance+obstacles.get(obstacles.size()-1).width;
			}
				
			
		}
		ObstacleType chooseObstacleType() {
			Random typeChoose = new Random();
			int decType = typeChoose.nextInt(ObstacleType.values().length);
			
			return ObstacleType.values()[decType];
		}
		
		void update() {
			for(Obstacle o : obstacles) {
				o.y = Street.this.streetY+lanePositionY;
				o.x += speed;
			}
			for(int kl = 0; kl < obstacles.size(); kl++) {
				if(obstacles.get(kl).x > ColorDash.WIDTH || obstacles.get(kl).x < 0) {
					Obstacle temp = obstacles.get(kl);
					temp = null;
					obstacles.remove(obstacles.get(kl));
				}
			}
			if(speed < 0) {
				if(obstacles.get(obstacles.size()-1).x-obstacles.get(obstacles.size()-1).width <= ColorDash.WIDTH-obstacleDistance) {
					
					obstacles.add(new Obstacle(ColorDash.WIDTH, Street.this.streetY+lanePositionY, chooseObstacleType(), Direction.LEFT, chooseRandomColor()));
					obstacleDebug(obstacles.get(obstacles.size()-1), "update-less");
				
				}
			}
			else if(speed > 0) {
				//System.out.println(obstacles.get(obstacles.size()-1).obstacleColor.toString());
				if(obstacles.get(obstacles.size()-1).x >= obstacleDistance) {
					obstacles.add(new Obstacle(0, Street.this.streetY+lanePositionY, chooseObstacleType(), Direction.RIGHT, chooseRandomColor()));
					obstacleDebug(obstacles.get(obstacles.size()-1), "update-greater");
					
				}
			}
			
			
		}
		void drawLane(Graphics g) {
			for(Obstacle o : obstacles) {
				o.draw(g);
			}
		}
		
		void obstacleDebug(Obstacle toDebug, String function) {
			toDebug.collisionBox.setBounds(toDebug.x, toDebug.y, toDebug.width, toDebug.height);
			boolean isCollision = false;
			for(Obstacle d : obstacles) {
				if(d.collisionBox.intersects(toDebug.collisionBox)) {
					if(d != toDebug) {
						//hSystem.out.println(function);
						isCollision = true;
						break;
					}
					
				}
			}
			if(isCollision == true) {
				obstacles.remove(toDebug);
				toDebug = null;
			}
		}
	}
}