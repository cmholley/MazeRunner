package com.ralitski.maze.world;

import com.ralitski.maze.block.Material;
import com.ralitski.maze.block.Materials;

public class Chunk {
	
	public static int getChunkCoordOf(int i) {
		boolean neg = i < 0;
		if(neg) i = -i - 1;
		int j = (i - (i % SIZE)) / SIZE;
		return neg ? -j - 1 : j;
	}
	
	public static int getInternalCoordOf(int i) {
		if(i < 0) i = (SIZE - (-i % SIZE));
		return i % SIZE;
	}
	
	public static final int SIZE = 8;
	public static final int BITS = (int)(Math.log(SIZE) / Math.log(2));
	
	//non-static
	
	private int x;
	private int y;
	private Level level;
	private boolean dirty;
	
	private Material[] materials;
	private int[] data;
	
	private Object customRenderData;
	
	//TODO: store tile entities
	//account for materials that don't render with the chunk
	
	public Chunk(Level level, int x, int y) {
		this.level = level;
		this.x = x;
		this.y = y;
		dirty = true;
		materials = new Material[SIZE * SIZE];
		data = new int[SIZE * SIZE];
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
//	//for use by chunk storage implementations only!
//	
//	public void setX(int x) {
//		this.x = x;
//	}
//	
//	public void setY(int y) {
//		this.y = y;
//	}
//	
//	//end
	
	public int getBlockX() {
		return x * SIZE;
	}
	
	public int getBlockY() {
		return y * SIZE;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public void setCustomRenderData(Object customRenderData) {
		this.customRenderData = customRenderData;
	}
	
	public Object getCustomRenderData() {
		return customRenderData;
	}
	
	private int getIndex(int x, int y) {
		return x + (y * SIZE);
	}
	
	public void setMaterial(int x, int y, Material mat) {
		setMaterialAndData(x, y, mat, 0);
	}
	
	public void setMaterialAndData(int x, int y, Material mat, int data) {
		//System.out.println(Integer.toBinaryString(x));
		int index = getIndex(x, y);
		materials[index] = mat;
		this.data[index] = data;
		dirty = true;
	}
	
	public void setData(int x, int y, int data) {
		this.data[getIndex(x, y)] = data;
		dirty = true;
	}
	
	public Material getMaterial(int x, int y) {
		Material mat = materials[getIndex(x, y)];
		return mat != null ? mat : Materials.EMPTY;
	}
	
	public int getData(int x, int y) {
		return data[getIndex(x, y)];
	}
	
	public String toString() {
		return "Chunk(" + level.getName() + ", " + x + ", " + y + ")";
	}
}
