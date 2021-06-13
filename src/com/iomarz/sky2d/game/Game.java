package com.iomarz.sky2d.game;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.iomarz.sky2d.display.Window;
import com.iomarz.sky2d.input.KeyInput;
import com.iomarz.sky2d.input.MouseInput;
import com.iomarz.sky2d.state.SwitchState;

public class Game implements Runnable {
	
	private int buffers;
	private int fps = 60;
	
	private Window win;
	
	private Thread thread;
	private boolean running = false;
	
	private Graphics g;
	private BufferStrategy bs;
	
	private SwitchState switchState;
	
	private KeyInput keys;
	private MouseInput mouse;
	
	public Game(int buffers) {
		this.buffers = buffers;
		keys = new KeyInput();
		mouse = new MouseInput();
	}
	
	// Initialize things 
	private void init() {
		win = new Window(800, 600, "Pong Game");
		// By default the game state (with id 1) is loaded first
		switchState = new SwitchState(1, this);
		// switchState.stateSwitch(0);
		win.getFrame().addKeyListener(keys);
		win.getCanvas().addMouseListener(mouse);
	}
	
	// Game Loop
	public void run() {
		init();
		
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		String tempTitle = win.getTitle();
		
		while (running) {
			// Sync for Unix-based OSes
			Toolkit.getDefaultToolkit().sync();
			// Rendering and ticking every frame (in theory)
			// This is too confusing to even explain... 
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000) {
				win.getFrame().setTitle(tempTitle + " (FPS: " + ticks + ")");
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	// Rendering
	private void render() {
		// I don't know another way of drawing 2d graphics to the screen.
		// Oh well.
		bs = win.getCanvas().getBufferStrategy();
		if (bs == null) {
			// Make sure you create the buffer strategy! (and by "you" i mean the program...)
			win.getCanvas().createBufferStrategy(buffers);
			return;
		}
		// Get draw graphics from the buffer strategy object
		g = bs.getDrawGraphics();
		// Clear the screen to avoid screen flicker
		g.clearRect(0, 0, win.getWidth(), win.getHeight());
		// Main render method (draw here lol)
		
		/*
		g.fillRect(0, 0, win.getWidth(), win.getHeight());
		g.drawImage(assets.getTestImg(), 0, 0, 128, 128, null);
		*/
		
		switchState.render(g);
		
		// Show n' dispose
		bs.show();
		g.dispose();
	}
	
	// Ticking
	private void tick() {
		switchState.tick();
		keys.tick();
		mouse.tick();
	}
	
	// Thread shit
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Thread failed to stop :(");
		}
	}
	
	// Getters
	public Window getWin() {
		return win;
	}
	
	public SwitchState getSwitchState() {
		return switchState;
	}
	
	public KeyInput getKeys() {
		return keys;
	}
	
	public MouseInput getMouse() {
		return mouse;
	}

}
