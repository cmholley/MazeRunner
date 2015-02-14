package com.ralitski.maze;

import com.ralitski.util.math.geom.d2.Point2d;

public class Player {
	
	private Game game;
	
	public Player(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Point2d getLocation() {
		return game.getPlayerLocation();
	}
	
	public void teleport(Location loc) {
		game.setCurrentLevel(loc.getLevel());
		Point2d p = getLocation();
		p.setX(loc.getX());
		p.setY(loc.getY());
	}
}
