package com.iomarz.sky2d.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	private int width, height;
	private String title;
	
	private JFrame frame;
	private Canvas canvas;
	
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		createFrame();
	}
	
	// Window
	private void createFrame() {
		frame = new JFrame(title);
		
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		createCanvas();
	}
	
	private void createCanvas() {
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		configureFrame();
	}
	
	private void configureFrame() {
		frame.add(canvas);
		frame.pack();
	}
	
	// Getters
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getTitle() {
		return title;
	}

}
