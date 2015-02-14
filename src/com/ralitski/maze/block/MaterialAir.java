package com.ralitski.maze.block;

import com.ralitski.maze.block.render.BlockRenderer;
import com.ralitski.maze.block.render.BlockRenderers;
import com.ralitski.maze.world.Level;

public class MaterialAir extends Material {

	public MaterialAir(int id) {
		super(id);
	}
    
    public BlockRenderer<? extends Material> getBlockRenderer() {
        return BlockRenderers.NON;
    }
    
    public boolean renderFace(int x, int y, int z, BlockFace face, Level level) {
        return false;
    }
    
    public boolean isSolid() {
        return false;
    }

}
