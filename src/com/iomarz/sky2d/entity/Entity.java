package com.iomarz.sky2d.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.iomarz.sky2d.game.Game;

public abstract class Entity {
	
	protected float x, y;
	protected int width, height;
	
	protected float speed;
	protected int health;
	protected boolean alive;
	
	protected Game game;
	
	public Entity(float x, float y, int width, int height, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
		// Defaults
		speed = 3;
		health = 10;
		alive = false;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public void hit(int dmg) {
		health -= dmg;
		if (health <= 0) {
			alive = false;
		}
	}
	
	// Bounding box
	public Rectangle getBounds() {
		return (new Rectangle((int) x, (int) y, width, height));
	}
	
	// Getters
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	// Setters
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

}
