package com.ralitski.maze;

import com.ralitski.util.math.geom.d2.Point2d;

public class Location {
	
	private String level;
	private float x;
	private float y;
	
	public Location(String level) {
		this(level, .5F, .5F);
	}
	
	public Location(String level, Point2d p) {
		this(level, p.getX(), p.getY());
	}
	
	public Location(String level, float x, float y) {
		this.level = level;
		this.x = x;
		this.y = y;
	}
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
