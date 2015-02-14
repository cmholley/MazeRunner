package com.ralitski.maze.block.render;

import org.lwjgl.opengl.GL11;

import com.ralitski.maze.block.BlockFace;
import com.ralitski.maze.block.Material;
import com.ralitski.maze.world.Level;

public class BlockRendererFloor extends BlockRenderers<Material> {

	@Override
	public void render(int x, int y, Level level, Material mat) {
		GL11.glPushMatrix();
		
		
		int data = level.getBlockData(x, y);
		if(mat.isTextured(data)) {
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			mat.getTexture(data).glBind();

	        GL11.glBegin(GL11.GL_QUADS);
	        {
				mat.getColor(level, x, y).glColor();
	            GL11.glTexCoord2f(0, 1);
	            GL11.glVertex3f(0, 0, 0);

				mat.getColor(level, x, y + 1).glColor();
	            GL11.glTexCoord2f(1, 1);
	            GL11.glVertex3f(0, 0, 1);

				mat.getColor(level, x + 1, y + 1).glColor();
	            GL11.glTexCoord2f(1, 0);
	            GL11.glVertex3f(1, 0, 1);

				mat.getColor(level, x + 1, y).glColor();
	            GL11.glTexCoord2f(0, 0);
	            GL11.glVertex3f(1, 0, 0);
	        }
	        GL11.glEnd();
		} else {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
	        GL11.glBegin(GL11.GL_QUADS);
	        {
				mat.getColor(level, x, y).glColor();
	            GL11.glVertex3f(0, 0, 0);
				mat.getColor(level, x, y + 1).glColor();
	            GL11.glVertex3f(0, 0, 1);
				mat.getColor(level, x + 1, y + 1).glColor();
	            GL11.glVertex3f(1, 0, 1);
				mat.getColor(level, x + 1, y).glColor();
	            GL11.glVertex3f(1, 0, 0);
	        }
	        GL11.glEnd();
		}
		
		GL11.glPopMatrix();
	}

}
