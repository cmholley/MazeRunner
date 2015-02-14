package com.ralitski.maze.block;

import com.ralitski.maze.block.render.BlockRenderer;
import com.ralitski.maze.block.render.BlockRenderers;
import com.ralitski.maze.world.Level;

public class MaterialFloor extends MaterialTextured {

	public MaterialFloor(int id) {
		super(id);
	}

	public MaterialFloor(int id, TextureHolder texture) {
		super(id, texture);
	}
    
    public boolean renderFace(int x, int y, BlockFace face, Level level) {
        return true;
    }
    
    public BlockRenderer<? extends Material> getBlockRenderer() {
        return BlockRenderers.FLOOR;
    }
    
    public boolean isSolid() {
        return false;
    }

}
