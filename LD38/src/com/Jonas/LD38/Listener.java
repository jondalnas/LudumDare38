package com.Jonas.LD38;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listener implements KeyListener, FocusListener, MouseMotionListener, MouseListener {
	public boolean[] keys = new boolean[65536];
	public int mouseButtons;
	public int mousePosX, mousePosY;
	public boolean dragging;
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {}

	public void mouseDragged(MouseEvent e) {
		mousePosX = (int) Math.floor((e.getX()*(960.0/970.0))/6);
		mousePosY = (int) Math.floor((e.getY()*(960.0/970.0))/6);
		
		if ((mouseButtons&0b1)==0) return;
		
		dragging = true;
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mouseButtons += Math.pow(2, e.getButton()-1);
		
		mousePosX = (int) Math.floor((e.getX()*(960.0/970.0))/6);
		mousePosY = (int) Math.floor((e.getY()*(960.0/970.0))/6);

		if ((mouseButtons&0b1)==0) return;
		if (dragging) return;
	}

	public void mouseReleased(MouseEvent e) {
		mouseButtons -= Math.pow(2, e.getButton()-1);
		
		dragging = false;
	}

	public void focusGained(FocusEvent arg0) {
		
	}

	public void focusLost(FocusEvent arg0) {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
		
		mouseButtons = 0;
	}
}