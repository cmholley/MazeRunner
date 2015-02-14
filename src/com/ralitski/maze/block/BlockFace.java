package com.ralitski.maze.block;

import org.lwjgl.opengl.GL11;

import com.ralitski.maze.block.render.BlockRenderers;
import com.ralitski.util.render.img.Color;

/**
 *
 * @author ralitski
 */
public enum BlockFace {
    
//	TOP(0, 0),
//	BOTTOM(0, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);
    
    private int x, y;
    
    BlockFace(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public BlockFace opposite() {
    	int index = ordinal() + 2;
    	BlockFace[] values = values();
    	return values[index % values.length];
    }
    
    //render helpin

    public void renderFace() {
    	switch(this) {
    	case UP:
    		BlockRenderers.renderUpFace();
    		return;
    	case DOWN:
    		BlockRenderers.renderDownFace();
    		return;
    	case LEFT:
    		BlockRenderers.renderLeftFace();
    		return;
    	case RIGHT:
    		BlockRenderers.renderRightFace();
    		return;
    	}
    }

    public void renderColoredFace(Color color, Color xColor, Color yColor, Color xyColor) {
    	switch(this) {
    	case UP:
    		BlockRenderers.renderColoredUpFace(color, xColor, yColor, xyColor);
    		return;
    	case DOWN:
    		BlockRenderers.renderColoredDownFace(color, xColor, yColor, xyColor);
    		return;
    	case LEFT:
    		BlockRenderers.renderColoredLeftFace(color, xColor, yColor, xyColor);
    		return;
    	case RIGHT:
    		BlockRenderers.renderColoredRightFace(color, xColor, yColor, xyColor);
    		return;
    	}
    }

    public void renderFaceTex(float x, float y) {
    	switch(this) {
    	case UP:
    		BlockRenderers.renderUpFaceTex(x, y);
    		return;
    	case DOWN:
    		BlockRenderers.renderDownFaceTex(x, y);
    		return;
    	case LEFT:
    		BlockRenderers.renderLeftFaceTex(x, y);
    		return;
    	case RIGHT:
    		BlockRenderers.renderRightFaceTex(x, y);
    		return;
    	}
    }

    public void renderColoredFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
    	switch(this) {
    	case UP:
    		BlockRenderers.renderColoredUpFaceTex(x, y, color, xColor, yColor, xyColor);
    		return;
    	case DOWN:
    		BlockRenderers.renderColoredDownFaceTex(x, y, color, xColor, yColor, xyColor);
    		return;
    	case LEFT:
    		BlockRenderers.renderColoredLeftFaceTex(x, y, color, xColor, yColor, xyColor);
    		return;
    	case RIGHT:
    		BlockRenderers.renderColoredRightFaceTex(x, y, color, xColor, yColor, xyColor);
    		return;
    	}
    }
}
