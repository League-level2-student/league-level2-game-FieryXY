package com.fieryxy;

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
	
	
	
	EndlessObjectManager(Soldier player) {
		this.player = player;
	}
	
	
	void draw(Graphics g) {
		
		for(Street s : streetArr) {
			s.draw(g);
		}
		player.draw(g);
	}
	void update() {
		System.out.println(isScrolling);
		for(Street s : streetArr) {
			s.update();
		}
		for(int j = 0; j < streetArr.size(); j++) {
			if(streetArr.get(j).y > ColorDash.HEIGHT) {
				streetArr.set(j, null);
				streetArr.remove(streetArr.get(j));
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		int decision = random.nextInt(4);
		
		if(decision == 0) {
			streetArr.add(new Street(0,-150, random.nextInt(2)+1, StreetType.TWO_WAY, this));
		}
		else if(decision == 1) {
			streetArr.add(new Street(0,-100,random.nextInt(2)+1, StreetType.ONE_WAY, this));
		}
		else if(decision == 2) {
			//streetArr.add(new Street(0,-100,100, this));	
			}
		else if(decision == 3) {
			streetArr.add(new Street(0,-100, random.nextInt(2)+1, StreetType.ONE_WAY, this));
		}
	
		
	}
}