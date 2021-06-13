package com.iomarz.sky2d.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.iomarz.sky2d.game.Game;

public class PlayerEntity extends Entity {

	public boolean AI;
	
	public PlayerEntity(float x, float y, int width, int height, Game game, boolean AI) {
		super(x, y, width, height, game);
		this.AI = AI;
		health = 20;
		alive = true;
		speed = 5.0f;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public void tick() {
		checkInputs();
	}
	
	private void checkInputs() {
		if (!AI) {
			if (game.getKeys().up) {
				y -= speed;
			}
			if (game.getKeys().down) {
				y += speed;
			}
		}
	}

}
