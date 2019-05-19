package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstacle {
	int x;
	int y;
	int[] widthValues = {30, 50, 40};
	int[] heightValues = {30, 40, 30};
	int width;
	int height;
	
	public enum ObstacleType {
		SOLDIER, TANK, TRUCK;
	}
	public enum Direction {
		LEFT, RIGHT;
	}
	
	Direction direction;
	ObstacleType type;
	Color obstacleColor;
	
	Obstacle(int x, int y, ObstacleType type, Direction direction, Color obstacleColor) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.obstacleColor = obstacleColor;
		this.direction = direction;
		getValues();
	}
	
	void getValues() {
		if(type == ObstacleType.SOLDIER) {
			width = widthValues[0];
			height = heightValues[0];
			if(obstacleColor == Color.RED) {
				
			}
			else if(obstacleColor == Color.GREEN) {
				//Green Soldier

			}
			else if(obstacleColor == Color.BLUE) {
				//Blue Soldier

			}
			else if(obstacleColor == Color.MAGENTA) {
				//Magenta or Purple Soldier
			}
		}
		else if(type == ObstacleType.TANK) {
			width = widthValues[1];
			height = heightValues[1];
			if(obstacleColor == Color.RED) {
				//Red Tank
			}
			else if(obstacleColor == Color.GREEN) {
				//Green Tank
			}
			else if(obstacleColor == Color.BLUE) {
				//Blue Tank
			}
			else if(obstacleColor == Color.MAGENTA) {
				//Magenta or Purple Tank
			}
		}
		else if(type == ObstacleType.TRUCK) {
			width = widthValues[2];
			height = heightValues[2];
			if(obstacleColor == Color.RED) {
				//Red Truck
			}
			else if(obstacleColor == Color.GREEN) {
				//Green Truck
			}
			else if(obstacleColor == Color.BLUE) {
				//Blue Truck
			}
			else if(obstacleColor == Color.MAGENTA) {
				//Magenta or Purple Truck
			}
		}
	}
	
	void draw(Graphics g) {
		g.setColor(obstacleColor);
		g.fillRect(x, y, width, height);
		if(direction == Direction.LEFT) {
			
		}
		else if(direction == Direction.RIGHT) {
			
		}
	}
	

}
