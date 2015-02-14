package com.ralitski.maze.world;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Maze {
	
	private String name;
	private String defaultLevel;
	private Map<String, Level> levels;
	
	public Maze(String name) {
		this.name = name;
		this.levels = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public Set<String> getLevelNames() {
		return levels.keySet();
	}
	
	public Collection<Level> getLevels() {
		return levels.values();
	}
	
	public Level getLevel(String name) {
		return levels.get(name);
	}
	
	public void addLevel(Level level) {
		levels.put(level.getName(), level);
	}
	
	public void removeLevel(Level level) {
		removeLevel(level.getName());
	}
	
	public void removeLevel(String name) {
		levels.remove(name);
	}
	
	public void setDefaultLevelName(String defaultLevel) {
		this.defaultLevel = defaultLevel;
	}
	
	public String getDefaultLevelName() {
		return defaultLevel;
	}
	
	public Level getDefaultLevel() {
		return levels.get(defaultLevel);
	}
}
