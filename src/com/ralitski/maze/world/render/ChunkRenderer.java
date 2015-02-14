package com.ralitski.maze.world.render;

import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;

import com.ralitski.maze.block.Material;
import com.ralitski.maze.block.render.BlockRenderer;
import com.ralitski.maze.world.Chunk;
import com.ralitski.util.render.list.ListMaker;

public class ChunkRenderer extends ListMaker {
	
	private Chunk chunk;
	
	public ChunkRenderer(Chunk chunk) {
		this.chunk = chunk;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void makeList() {
		GL11.glPushMatrix();
		int bX = chunk.getBlockX();
		int bY = chunk.getBlockY();
		GL11.glTranslatef(bX, 0, bY);
		for(int x = 0; x < Chunk.SIZE; x++) {
			for(int y = 0; y < Chunk.SIZE; y++) {
				
				Material m = chunk.getMaterial(x, y);
				
				if(m.renderWithLevel()) {
					GL11.glPushMatrix();
					GL11.glTranslatef(x, 0, y);
					//System.out.println(m.getId());
					
					//check out this type unsafety, yo
					BlockRenderer renderer = m.getBlockRenderer();
					try {
						renderer.render(bX + x, bY + y, chunk.getLevel(), m);
					} catch(ClassCastException ex) {
						//renderer nope
						Logger.getLogger(getClass().getName())
						.log(java.util.logging.Level.WARNING, "Material " + m.getId() + " is registered with an invalid block renderer");
					}
					
					GL11.glPopMatrix();
				} else {
					//uh....hm
				}
			}
		}
		GL11.glPopMatrix();
	}

}
