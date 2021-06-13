package com.iomarz.sky2d.state;

import java.awt.Graphics;

import com.iomarz.sky2d.game.Game;

public abstract class State {
	
	protected Game game;
	protected int id;
	
	public State(Game game) {
		this.game = game;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	// Getters
	public Game getGame() {
		return game;
	}
	
	public int getId() {
		return id;
	}

}
