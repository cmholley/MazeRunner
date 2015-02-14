package com.ralitski.maze;

import java.util.Random;

import org.lwjgl.input.Mouse;

import com.ralitski.maze.block.BlockFace;
import com.ralitski.maze.block.Material;
import com.ralitski.maze.block.Materials;
import com.ralitski.maze.world.Level;
import com.ralitski.maze.world.Maze;
import com.ralitski.util.gui.GuiDebug;
import com.ralitski.util.input.InputMonitor;
import com.ralitski.util.input.MapInputUser;
import com.ralitski.util.render.RenderManager;
import com.ralitski.util.render.RenderManagerUser;
import com.ralitski.util.render.camera.BasicCamera;
import com.ralitski.util.render.camera.Camera;
import com.ralitski.util.render.camera.CameraOwner;
import com.ralitski.util.render.display.AbstractDisplayUser;
import com.ralitski.util.render.img.ImageList;

public class GameManager extends AbstractDisplayUser implements RenderManagerUser, CameraOwner {
	
	private static GameManager instance;
	
	public static GameManager getInstance() {
		return instance;
	}
	
	private InputMonitor input;
	private MapInputUser inputUser;
	private Camera camera;
	private RenderManager render;
	private ImageList images;
	
	private Game gameInstance;
	private GuiDebug gui;
	
	//stuffs
	
	public Game getGameInstance() {
		return gameInstance;
	}

	@Override
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera c) {
		camera = c;
		inputUser.set("CAMERA", c);
		if(gameInstance != null) gameInstance.setCamera(c);
		render.setCamera(c);
	}
	
	public void startGame(Maze maze) {
		gameInstance = new Game(maze, camera);
		//close guis
	}
	
	//display methods

	@Override
	public String title() {
		return "Maze Runner";
	}

	@Override
	public void setup() {
		instance = this;
		
		//le
		images = new ImageList();
		Materials.init(images);
		
		//stoofs
		
		inputUser = new MapInputUser();
		input = new InputMonitor(inputUser);
		//render camera is set in setCamera()
		render = new RenderManager(this, null);
		setCamera(new BasicCamera());
		
		//testing
		
		gui = new GuiDebug(this);
		
		Maze m = new Maze("le");
		Level l = new Level("lele");
		m.addLevel(l);
		m.setDefaultLevelName(l.getName());
		int RANGE = 100;
		for(int x = -RANGE; x < RANGE; x++) {
			for(int y = -RANGE; y < RANGE; y++) {
				//ensure spawn is open
				if(x < 0 || x > 0 || y < 0 || y > 0) {
					l.setBlock(x, y, Materials.WALL);
				} else {
					l.setBlock(x, y, Materials.FLOOR);
				}
			}
		}
		int x = 0;
		int y = 0;
		Random r = new Random();
		for(int i = 0; i < 500;) {
			Material mat = l.getBlock(x, y);
			if(mat.collideWithPlayer()) {
				int adj = 0;
				for(BlockFace face : BlockFace.values()) {
					if(l.getBlock(x + face.getX(), y + face.getY()).collideWithPlayer()) {
						adj++;
					}
				}
				if(adj > 2 && adj < 4) {
					l.setBlock(x, y, Materials.FLOOR);
					i++;
				}
			}
			if(r.nextBoolean()) {
				x += r.nextInt(3) - 1;
			} else {
				y += r.nextInt(3) - 1;
			}
		}
		startGame(m);
	}

	@Override
	public void close() {
		if(gameInstance != null) {
			gameInstance.close();
		}
	}

	@Override
	public void resize() {
		render.getCamera().setup3D();
	}
	
	@Override
	public void updateTick() {
		input.update();
		camera.update();
		if(gameInstance != null) {
			gameInstance.update();
		}
	}

	@Override
	public void updateRender(float partial) {
		render.render(partial);
	}

	@Override
	public void render3d(float partial) {
		if(gameInstance != null) gameInstance.render(partial);
	}

	@Override
	public void render2d(float partial) {
		//render gui
		gui.render();
	}

}
