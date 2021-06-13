package com.iomarz.sky2d.state;

import java.awt.Graphics;

import com.iomarz.sky2d.game.Game;
import com.iomarz.sky2d.state.states.GameState;
import com.iomarz.sky2d.state.states.MenuState;

public class SwitchState {
	
	private int startState;
	private int curState;
	
	private Game game;
	
	private MenuState menuState;
	private GameState gameState;
	
	public SwitchState(int startState, Game game) {
		this.startState = startState;
		this.game = game;
		curState = startState;
		initStates();
	}
	
	private void initStates() {
		menuState = new MenuState(game);
		gameState = new GameState(game);
	}
	
	public void stateSwitch(int nextState) {
		curState = nextState;
		System.out.println("State with the id " + curState + " loaded!");
	}
	
	public void render(Graphics g) {
		if (curState == 0) {
			menuState.render(g);
		} else if (curState == 1) {
			gameState.render(g);
		}
	}
	
	public void tick() {
		if (curState == 0) {
			menuState.tick();
		} else if (curState == 1) {
			gameState.tick();
		}
	}

}
