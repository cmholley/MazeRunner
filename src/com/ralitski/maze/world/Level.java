package com.ralitski.maze.world;

import java.util.Iterator;

import com.ralitski.maze.block.Material;
import com.ralitski.maze.block.Materials;
import com.ralitski.maze.color.ColorGenerator;
import com.ralitski.maze.color.FunctionalColorGenerator;
import com.ralitski.maze.color.SmoothColorGenerator;
import com.ralitski.util.render.img.Color;

public class Level {
	
	private static ChunkStorage getDefaultChunkStorage() {
		return new ChunkStorageImpl();
	}
	
	private static ColorGenerator getDefaultColorGenerator() {
		//return FunctionalColorGenerator.gradient(Color.BLUE, Color.RED, Chunk.SIZE * 4);
		return new SmoothColorGenerator()
		.setColorStart(Color.BLUE)
		.setColorEnd(Color.RED)
		.setSize(40);
	}
	
	private String name;
	private ChunkStorage chunks;
	private ColorGenerator colors;
	
	public Level(String name) {
		this.name = name;
		chunks = getDefaultChunkStorage();
		colors = getDefaultColorGenerator();
	}

	public String getName() {
		return name;
	}
	
	public ColorGenerator getColorGenerator() {
		return colors;
	}
	
	public void setColorGenerator(ColorGenerator gen) {
		this.colors = gen;
	}
	
	public ChunkStorage getChunkStorage() {
		return chunks;
	}
	
	public void setChunkStorage(ChunkStorage storage) {
		if(storage != null) {
			if(chunks != null) {
				for(Chunk c : chunks) {
					storage.setChunkAt(c.getX(), c.getY(), c);
				}
			}
			this.chunks = storage;
		} else {
			throw new IllegalArgumentException("Level chunk storage can't be null!");
		}
	}
	
	//chunk management
	
	public Chunk getOrCreateChunkAt(int x, int y) {
		return getOrCreateChunkFromChunkCoords(Chunk.getChunkCoordOf(x), Chunk.getChunkCoordOf(y));
	}

	private Chunk getOrCreateChunkFromChunkCoords(int x, int y) {
		Chunk c = chunks.getChunkAt(x, y);
		if(c == null) {
			c = new Chunk(this, x, y);
			chunks.setChunkAt(x, y, c);
		}
		return c;
	}
	
	public Chunk getChunkAt(int x, int y) {
		return getChunkFromChunkCoords(Chunk.getChunkCoordOf(x), Chunk.getChunkCoordOf(y));
	}
	
	private Chunk getChunkFromChunkCoords(int x, int y) {
		return chunks.getChunkAt(x, y);
	}
	
	public Chunk deleteChunk(int x, int y) {
		Chunk c = getChunkAt(x, y);
		if(c != null) chunks.setChunkAt(x, y, null);
		return c;
	}
	
	//get values

	public Material getBlock(int x, int y) {
		Chunk c = getChunkAt(x, y);
		return c != null ? c.getMaterial(Chunk.getInternalCoordOf(x), Chunk.getInternalCoordOf(y)) : Materials.EMPTY;
	}
	
	public int getBlockData(int x, int y) {
		Chunk c = getChunkAt(x, y);
		return c != null ? c.getData(Chunk.getInternalCoordOf(x), Chunk.getInternalCoordOf(y)) : 0;
	}
	
	//set values
	
	public void setBlock(int x, int y, Material mat) {
		Chunk c = getOrCreateChunkAt(x, y);
		c.setMaterial(Chunk.getInternalCoordOf(x), Chunk.getInternalCoordOf(y), mat);
	}
	
	public void setBlockData(int x, int y, int data) {
		Chunk c = getOrCreateChunkAt(x, y);
		c.setData(Chunk.getInternalCoordOf(x), Chunk.getInternalCoordOf(y), data);
	}
	
	public void setBlockAndData(int x, int y, Material mat, int data) {
		Chunk c = getOrCreateChunkAt(x, y);
		c.setMaterialAndData(Chunk.getInternalCoordOf(x), Chunk.getInternalCoordOf(y), mat, data);
	}
	
	//misc

	public Color getColor(int x, int y) {
		return colors.getColor(x, y);
	}
}
