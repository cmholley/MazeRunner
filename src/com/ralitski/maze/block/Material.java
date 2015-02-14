package com.ralitski.maze.block;

import com.ralitski.maze.block.render.BlockRenderer;
import com.ralitski.maze.block.render.BlockRenderers;
import com.ralitski.maze.registry.RegistryException;
import com.ralitski.maze.world.Level;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.Texture;

public class Material {
	
	//TODO: get texture by (x, y) instead of (data)
	
	private static final int COUNT = 255;
	private static final Material[] VALUES = new Material[COUNT];
	
	public static Material getMaterial(int id) {
		//assume first material is the default material
		return id < COUNT ? (id < 0 ? VALUES[0] : VALUES[id]) : (VALUES[id]);
	}
	
	private int id;
	
	public Material(int id) {
		if(VALUES[id] == null) {
			VALUES[id] = this;
			this.id = id;
		} else {
			throw new RegistryException("Unable to register material with id " + id);
		}
	}
	
	public int getId() {
		return id;
	}
    
    public boolean renderFace(int x, int y, BlockFace face, Level level) {
        return isSolid() ? !level.getBlock(x + face.getX(), y + face.getY()).isSolid() : true;
    }
    
    public BlockRenderer<? extends Material> getBlockRenderer() {
        return BlockRenderers.WALL;
    }
    
    public boolean isSolid() {
        return true;
    }
    
	/**
	 * if this material will be rendered in a single display list when the level
	 * is loaded, or if it will be rendered each tick
	 * 
	 * @return the render behavior of this material
	 */
    public boolean renderWithLevel() {
        return true;
    }

	public Color getColor(Level level, int x, int y) {
		return level.getColor(x, y);
	}

	public boolean isTextured(int data, BlockFace face) {
		return isTextured(data);
	}

	public Texture getTexture(int data, BlockFace face) {
		return getTexture(data);
	}

	public boolean isTextured(int data) {
		return false;
	}

	public Texture getTexture(int data) {
		return null;
	}

	public boolean collideWithPlayer() {
		return isSolid();
	}
}
