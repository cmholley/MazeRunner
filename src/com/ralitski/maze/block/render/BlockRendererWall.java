package com.ralitski.maze.block.render;

import org.lwjgl.opengl.GL11;

import com.ralitski.maze.block.BlockFace;
import com.ralitski.maze.block.Material;
import com.ralitski.maze.block.MaterialTextured;
import com.ralitski.maze.world.Level;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.Texture;

/**
 * for use of this renderer type, please reference BlockRenderers.SIMPLE
 * 
 * @author ralitski
 */
public class BlockRendererWall extends BlockRenderers<Material> {
	
	protected static final float HEIGHT = 100;
	
	protected BlockRendererWall() {}

    @Override
    public void render(int x, int y, Level level, Material mat) {

        Color color = mat.getColor(level, x, y);
        Color xColor = mat.getColor(level, x + 1, y);
        Color yColor = mat.getColor(level, x, y + 1);
        Color xyColor = mat.getColor(level, x + 1, y + 1);

        int data = level.getBlockData(x, y);
        for(BlockFace face : BlockFace.values()) {
        	if(mat.renderFace(x, y, face, level)) {
        		GL11.glPushMatrix();
            	if(mat.isTextured(data, face)) {
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    Texture texture = mat.getTexture(data, face);
                    texture.glBind();
            		face.renderColoredFaceTex(1, HEIGHT, color, xColor, yColor, xyColor);
            	} else {
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glScalef(1, HEIGHT, 1);
            		face.renderColoredFace(color, xColor, yColor, xyColor);
            	}
            	GL11.glPopMatrix();
        	}
        }
    }
}
