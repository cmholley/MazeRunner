package com.ralitski.maze.world.store;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.ralitski.maze.world.Level;
import com.ralitski.maze.world.Maze;

public class WorldSaveManager {
	
	private File baseDir;
	
	public WorldSaveManager(File baseDir) {
		setBaseDir(baseDir);
	}
	
	public File getBaseDir() {
		return baseDir;
	}
	
	public void setBaseDir(File dir) {
		if(!dir.isDirectory()) throw new IllegalArgumentException("Specified directory \"" + dir.getPath() + "\" is not a directory!");
		this.baseDir = dir;
	}
	
	public List<String> getMazes() {
		List<String> mazes = new LinkedList<>();
		for(File file : baseDir.listFiles()) {
			if(file.isDirectory()) {
				mazes.add(file.getName());
			}
		}
		return mazes;
	}
	
	public void save(Maze maze) {
		File mazeDir = new File(baseDir, maze.getName());
		mazeDir.mkdir();
		for(Level level : maze.getLevels()) {
			File levelDir = new File(mazeDir, level.getName());
			levelDir.mkdir();
		}
		File info = new File(mazeDir, "info.txt");
		try {
			info.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//probably send actual data saving to another impl
	
	private void saveLevel(File dir, Level level) {
		
	}
	
	private void saveInfo(File dataFile, Maze maze) {
		
	}
	
	//TODO: loading stuffs
}
