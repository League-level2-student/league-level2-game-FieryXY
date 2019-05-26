package com.fieryxy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import com.fieryxy.Street.StreetType;

public class EndlessObjectManager implements ActionListener {
	
	Soldier player;
	ArrayList<Street> streetArr = new ArrayList<Street>();
	Random random = new Random();
	public boolean isScrolling = true;
	public long forwardMotion = 0;
	GamePanel gp;
	
	public int score = 0;
	
	Font menuMainFont = new Font("Arial", Font.PLAIN, 40);
	
	
	
	EndlessObjectManager(Soldier player, GamePanel gp) {
		this.player = player;
		this.gp = gp;
	}
	
	
	

	void draw(Graphics g) {
		
		for(Street s : streetArr) {
			s.draw(g);
		}
		player.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(menuMainFont);
		g.drawString(String.valueOf(score), 30, 30);
		
	}
	void update() {
		for(Street s : streetArr) {
			s.update();
		}
		for(int j = 0; j < streetArr.size(); j++) {
			if(streetArr.get(j).y > ColorDash.HEIGHT) {
				streetArr.set(j, null);
				streetArr.remove(streetArr.get(j));
				//score++;
			}
		}
		
		player.update();
		checkCollisions();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		int decision = random.nextInt(7);
		
		if(streetArr.size() == 0) {
			streetArr.add(new Street(0,-250, 1, StreetType.ONE_WAY, this));
		}
		
		else if(decision == 0) {
			streetArr.add(new Street(0, streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
		else if(decision == 1) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200,random.nextInt(2)+1, StreetType.ONE_WAY, this));
		}
		else if(decision == 2) {
			//streetArr.add(new Street(0,-100,100, this));	
			}
		else if(decision == 3) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.ONE_WAY, this));
		}
		else if(decision == 4) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
		else if(decision == 5) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
		else if(decision == 6) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
	
		
	}
	
	void checkCollisions() {
		for(Street s : streetArr) {
			for(Street.LaneTraffic l : s.laneArr) {
				for(Obstacle o : l.obstacles) {
					if(player.collisionBox.intersects(o.collisionBox)) {
						if(o.obstacleColor != player.color) {
							gp.endlessToMenu();
						}
						else {
							score++;
							o.x = ColorDash.WIDTH+50;
						}
						
					}
					
				}
			}
		}
	}
}