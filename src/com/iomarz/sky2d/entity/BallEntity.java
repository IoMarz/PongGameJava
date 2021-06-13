package com.iomarz.sky2d.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.iomarz.sky2d.game.Game;

public class BallEntity extends Entity {

	private boolean right = true;
	private Random rand;
	
	public int dirSpeed;
	
	public BallEntity(float x, float y, int width, int height, Game game) {
		super(x, y, width, height, game);
		speed = 6.0f;
		init();
	}
	
	private void init() {
		rand = new Random();
		dirSpeed = rand.nextInt((int) speed);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public void tick() {
		if (right) {
			x += speed;
			y += dirSpeed;
		} else {
			x -= speed;
			y -= dirSpeed;
		}
	}
	
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

}
