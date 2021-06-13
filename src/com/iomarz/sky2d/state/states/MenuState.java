package com.iomarz.sky2d.state.states;

import java.awt.Color;
import java.awt.Graphics;

import com.iomarz.sky2d.game.Game;
import com.iomarz.sky2d.state.State;

public class MenuState extends State {

	public MenuState(Game game) {
		super(game);
		id = 0;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, game.getWin().getWidth(), game.getWin().getHeight());
	}

	@Override
	public void tick() {
		
	}

}
