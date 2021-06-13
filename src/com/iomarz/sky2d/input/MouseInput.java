package com.iomarz.sky2d.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private boolean[] clicks;
	public boolean clickToMove;
	private MouseEvent m;
	
	public MouseInput() {
		clicks = new boolean[16];
	}
	
	public void tick() {
		clickToMove = clicks[MouseEvent.BUTTON1];
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		this.m = m;
		clicks[m.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		this.m = m;
		clicks[m.getButton()] = false;
	}
	
	public MouseEvent getMouseEvent() {
		return m;
	}

}
