package com.ralitski.maze;

import java.util.Random;

import com.ralitski.maze.block.Material;
import com.ralitski.maze.block.Materials;
import com.ralitski.maze.world.Level;
import com.ralitski.maze.world.Maze;
import com.ralitski.maze.world.render.WorldRenderManager;
import com.ralitski.util.BoundingBox;
import com.ralitski.util.BoundingBoxPositioner;
import com.ralitski.util.doc.TODO;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.render.camera.Camera;

public class Game {
	
	private static final float PLAYER_SIZE = .25F;
	
	private Maze maze;
	private Level currentLevel;
	private WorldRenderManager render;
	
	private Player player;
	private Camera camera;
	private BoundingBox playerBox;
	//reuse the loc, since it automatically uses the current camera
	private PlayerLoc loc;
	private float prevX;
	private float prevZ;
	
	public Game(Maze maze, Camera camera) {
		
		this.player = new Player(this);
		this.maze = maze;
		render = new WorldRenderManager();
		setCurrentLevel(maze.getDefaultLevel());
		this.camera = camera;
		loc = new PlayerLoc();
		playerBox = new BoundingBox(-PLAYER_SIZE, -PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
		playerBox.setCenter(loc);
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void update() {
		doCollisionChecks();
//		Random r = new Random();
//		if(r.nextInt(20) == 0) {
//			currentLevel.setBlock(r.nextInt(3000) - 1500, r.nextInt(3000) - 1500, r.nextBoolean() ? Materials.WALL : Materials.FLOOR);
//		}
	}
	
	//move to separate method to not clutter update()
	@TODO("better collision prevention")
	private void doCollisionChecks() {
		float cX = camera.getX();
		float cY = camera.getZ();
		if(cX != prevX || cY != prevZ) {
			int x = (int)Math.floor(cX);
			int z = (int)Math.floor(cY);
			//define here for reuse
			BoundingBox bb = new BoundingBox(0, 0, 1, 1);
			//the total force on the player
			Point2d push = new Point2d(0, 0);
			for(int i = x - 1; i <= x + 1; i++) {
				for(int j = z - 1; j <= z + 1; j++) {
					Material m = currentLevel.getBlock(i, j);
					if(m.collideWithPlayer()) {
						//set the center of the reusable box
						bb.setCenter(new Point2d(i, j));
						if(bb.intersecting(playerBox)) {
							float dX = camera.getX() - ((float)i) - .5F;
							float dY = camera.getZ() - ((float)j) - .5F;
							push.addX(dX > 0 ? 1F - dX : -1F + dX);
							push.addY(dY > 0 ? 1F - dY : -1F + dY);
						}
					}
				}
			}
			prevX = cX;
			prevZ = cY;
			push.setLength(.05F);
			loc.translate(push);
		}
//		Point2d prevPos = new Point2d(prevX, prevZ);
//		float cX = camera.getX();
//		float cY = camera.getZ();
//		if(cX != prevX || cY != prevZ) {
//			//define here for reuse
//			BoundingBox bb = new BoundingBox(0, 0, 1, 1);
//			
//			int x = (int)Math.floor(cX);
//			int z = (int)Math.floor(cY);
//			for(int i = x - 1; i <= x + 1; i++) {
//				for(int j = z - 1; j <= z + 1; j++) {
//					Material m = currentLevel.getBlock(i, j);
//					if(m.collideWithPlayer()) {
//						//set the center of the reusable box
//						bb.setCenter(new Point2d(i, j));
//						BoundingBoxPositioner.exclude(bb, playerBox, prevPos);
//					}
//				}
//			}
//			prevX = cX;
//			prevZ = cY;
//		}
	}
	
	public void render(float partial) {
		//renders the world
		render.render(partial);
	}
	
	public void close() {
		
	}
	
	//secret stuff
	
	void setCurrentLevel(String level) {
		if(level != null) {
			setCurrentLevel(maze.getLevel(level));
		}
	}
	
	private void setCurrentLevel(Level level) {
		if(level != null && (currentLevel == null || !currentLevel.getName().equals(level))) {
			this.currentLevel = level;
			prevX = prevZ = Float.NaN;
			render.setLevel(level);
		}
	}
	
	Point2d getPlayerLocation() {
		return loc;
	}
	
	private class PlayerLoc extends Point2d {

	    public void setX(float x) {
	        camera.setX(x);
	    }

	    public float getX() {
	        return camera.getX();
	    }

	    public void setY(float y) {
	        camera.setZ(y);
	    }

	    public float getY() {
	        return camera.getZ();
	    }
	}
}
