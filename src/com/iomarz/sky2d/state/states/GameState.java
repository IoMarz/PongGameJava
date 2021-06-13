package com.iomarz.sky2d.state.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.iomarz.sky2d.entity.BallEntity;
import com.iomarz.sky2d.entity.PlayerEntity;
import com.iomarz.sky2d.game.Game;
import com.iomarz.sky2d.state.State;

public class GameState extends State {
	
	private Random rand;
	
	private PlayerEntity player, ai;
	private BallEntity ball;
	
	private int pscore = 0, ascore = 0;
	private Font font42;
	
	private boolean borderendai = false, scored = false;
	
	public GameState(Game game) {
		super(game);
		id = 1;
		init();
	}
	
	private void init() {
		rand = new Random();
		font42 = new Font("TimesRoman", Font.BOLD, 42);
		player = new PlayerEntity(game.getWin().getWidth() - 40, (game.getWin().getHeight() / 2) - 60, 20, 120, game, false);
		ai = new PlayerEntity(game.getWin().getWidth() - (game.getWin().getWidth() - 20), (game.getWin().getHeight() / 2) - 60, 20, 120, game, true);
		
		ball = new BallEntity(game.getWin().getWidth() / 2 - 20, game.getWin().getHeight() / 2 - 20, 20, 20, game);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, game.getWin().getWidth(), game.getWin().getHeight());
		g.setColor(Color.white);
		g.setFont(font42);
		g.drawString("" + ascore, 320, 40);
		g.drawString("" + pscore, 420, 40);
		player.render(g);
		ai.render(g);
		ball.render(g);
	}

	@Override
	public void tick() {
		player.tick();
		ball.tick();
		checkEnds();
		checkCollision();
		checkGoal();
		System.out.println("p-score: " + pscore);
		System.out.println("a-score: " + ascore);
	}
	
	private void checkEnds() {
		if (player.getY() <= 0) {
			player.setY(0);
		}
		
		if (player.getY() >= 480) {
			player.setY(480);
		}
		
		if (ai.getY() <= 0) {
			ai.setY(0);
			borderendai = true;
		} else if (ai.getY() >= 480) {
			ai.setY(480);
			borderendai = true;
		} else {
			borderendai = false;
		}
		
		if (ball.getY() > game.getWin().getHeight() - 10) {
			ball.dirSpeed = -ball.dirSpeed;
		}
		
		if (ball.getY() < 0) {
			ball.dirSpeed = -ball.dirSpeed;
		}
	}
	
	private void checkCollision() {
		if (ball.getBounds().intersects(player.getBounds()) || ball.getBounds().intersects(ai.getBounds())) {
			if (ball.isRight()) {
				ball.setRight(false);
				ball.dirSpeed = rand.nextInt((int) ball.getSpeed());
			} else {
				ball.setRight(true);
				ball.dirSpeed = rand.nextInt((int) ball.getSpeed());
			}
		}
		
		if (ai.AI) {
			if (ball.dirSpeed <= 6 && !borderendai) {
				ai.setY(ball.getY() - 60);
			}
		}
	}
	
	private void checkGoal() {
		// Player Score
		if (ball.getX() < 10 && !scored) {
			pscore++;
			scored = true;
		}
		
		// AI Score
		if (ball.getX() > 790 && !scored) {
			ascore++;
			scored = true;
		}
		
		// Reset if scored
		if (scored) {
			resetLayout();
		}
		
		// Check who won
		if (pscore >= 10) {
			System.exit(0);
		}
		
		if (ascore >= 10) {
			System.exit(0);
		}
	}
	
	private void resetLayout() {
		ball.setX(game.getWin().getWidth() / 2 - 20);
		ball.setY(game.getWin().getHeight() / 2 - 20);
		scored = false;
		player.setX(game.getWin().getWidth() - 40);
		player.setY((game.getWin().getHeight() / 2) - 60);
		ai.setX(game.getWin().getWidth() - (game.getWin().getWidth() - 20));
		ai.setY((game.getWin().getHeight() / 2) - 60);
		borderendai = false;
	}

}
