package com.ralitski.maze.block.render;

import org.lwjgl.opengl.GL11;

import com.ralitski.maze.block.Material;
import com.ralitski.util.render.img.Color;

/**
 * though this is a utility class, with the inclusion of type arguments you may
 * extend this class to inherit the utility methods.
 * 
 * @author ralitski
 */
public abstract class BlockRenderers<M extends Material> implements BlockRenderer<M> {
    
    /**
     * a block renderer that doesn't render the block. used for air.
     */
    public static final BlockRenderer<? extends Material> NON = new BlockRendererNon();
    
    /**
     * simple block renderer, renders a 1x100x1 textured/colored cube. For a
     * colored cube, the material must implement the Colored interface (ral.util.img.Colored).
     */
    public static final BlockRenderer<? extends Material> WALL = new BlockRendererWall();

    /**
     * a simple block renderer that renders a flat floor panel.
     */
	public static final BlockRenderer<? extends Material> FLOOR = new BlockRendererFloor();
    
    public static void renderBlock() {
    	renderTopFace();
    	renderBottomFace();
        renderRightFace();
        renderLeftFace();
        renderUpFace();
        renderDownFace();
    }
    
    public static void renderBlockTex(float x, float y) {
    	renderTopFaceTex(x, y);
    	renderBottomFaceTex(x, y);
        renderRightFaceTex(x, y);
        renderLeftFaceTex(x, y);
        renderUpFaceTex(x, y);
        renderDownFaceTex(x, y);
    }

    public static void renderTopFace() {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex3f(0, 1, 0);
            GL11.glVertex3f(0, 1, 1);
            GL11.glVertex3f(1, 1, 1);
            GL11.glVertex3f(1, 1, 0);
        }
        GL11.glEnd();
    }

    public static void renderColoredTopFace(Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glVertex3f(0, 1, 0);
            yColor.glColor();
            GL11.glVertex3f(0, 1, 1);
            xyColor.glColor();
            GL11.glVertex3f(1, 1, 1);
            xColor.glColor();
            GL11.glVertex3f(1, 1, 0);
        }
        GL11.glEnd();
    }

    public static void renderTopFaceTex(float x, float y) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0, x);
            GL11.glVertex3f(0, y, 0);
            GL11.glTexCoord2f(x, x);
            GL11.glVertex3f(0, y, x);
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, y, x);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, 0);
        }
        GL11.glEnd();
    }

    public static void renderColoredTopFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            color.glColor();
            GL11.glTexCoord2f(0, x);
            GL11.glVertex3f(0, y, 0);

            yColor.glColor();
            GL11.glTexCoord2f(x, x);
            GL11.glVertex3f(0, y, x);

            xyColor.glColor();
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, y, x);

            xColor.glColor();
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, 0);
        }
        GL11.glEnd();
    }

    public static void renderBottomFace() {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex3f(0, 0, 0);
            GL11.glVertex3f(1, 0, 0);
            GL11.glVertex3f(1, 0, 1);
            GL11.glVertex3f(0, 0, 1);
        }
    }

    public static void renderBottomFaceTex(float x, float y) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(x, x);
            GL11.glVertex3f(0, 0, 0);
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, 0, 0);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, 0, x);
            GL11.glTexCoord2f(0, x);
            GL11.glVertex3f(0, 0, x);
        }
    }

    public static void renderColoredBottomFace(Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glVertex3f(0, 0, 0);
            xColor.glColor();
            GL11.glVertex3f(1, 0, 0);
            xyColor.glColor();
            GL11.glVertex3f(1, 0, 1);
            yColor.glColor();
            GL11.glVertex3f(0, 0, 1);
        }
    }

    public static void renderColoredBottomFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glTexCoord2f(x, x);
            GL11.glVertex3f(0, 0, 0);

            xColor.glColor();
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, 0, 0);

            xyColor.glColor();
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, 0, x);

            yColor.glColor();
            GL11.glTexCoord2f(0, x);
            GL11.glVertex3f(0, 0, x);
        }
    }

    public static void renderDownFace() {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex3f(0, 0, 0);
            GL11.glVertex3f(0, 1, 0);
            GL11.glVertex3f(1, 1, 0);
            GL11.glVertex3f(1, 0, 0);
        }
        GL11.glEnd();
    }

    public static void renderDownFaceTex(float x, float y) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(0, 0, 0);
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(0, y, 0);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, 0);
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(x, 0, 0);
        }
        GL11.glEnd();
    }

    public static void renderColoredDownFace(Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glVertex3f(0, 0, 0);
            //color.glColor();
            GL11.glVertex3f(0, 1, 0);
            xColor.glColor();
            GL11.glVertex3f(1, 1, 0);
            //xColor.glColor();
            GL11.glVertex3f(1, 0, 0);
        }
        GL11.glEnd();
    }

    public static void renderColoredDownFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(0, 0, 0);

        	//color.glColor();
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(0, y, 0);

            xColor.glColor();
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, 0);

            //xColor.glColor();
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(x, 0, 0);
        }
        GL11.glEnd();
    }

    public static void renderUpFace() {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex3f(0, 0, 1);
            GL11.glVertex3f(1, 0, 1);
            GL11.glVertex3f(1, 1, 1);
            GL11.glVertex3f(0, 1, 1);
        }
        GL11.glEnd();
    }

    public static void renderUpFaceTex(float x, float y) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(0, 0, x);
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(x, 0, x);
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, y, x);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(0, y, x);
        }
        GL11.glEnd();
    }

    public static void renderColoredUpFace(Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	yColor.glColor();
            GL11.glVertex3f(0, 0, 1);
            xyColor.glColor();
            GL11.glVertex3f(1, 0, 1);
            //xyColor.glColor();
            GL11.glVertex3f(1, 1, 1);
            yColor.glColor();
            GL11.glVertex3f(0, 1, 1);
        }
        GL11.glEnd();
    }

    public static void renderColoredUpFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            yColor.glColor();
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(0, 0, x);

            xyColor.glColor();
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(x, 0, x);

            //xyColor.glColor();
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, y, x);

            yColor.glColor();
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(0, y, x);
        }
        GL11.glEnd();
    }

    public static void renderRightFace() {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex3f(1, 0, 0);
            GL11.glVertex3f(1, 1, 0);
            GL11.glVertex3f(1, 1, 1);
            GL11.glVertex3f(1, 0, 1);
        }
        GL11.glEnd();
    }

    public static void renderRightFaceTex(float x, float y) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(x, 0, 0);
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, y, 0);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, x);
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(x, 0, x);
        }
        GL11.glEnd();
    }

    public static void renderColoredRightFace(Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	xColor.glColor();
            GL11.glVertex3f(1, 0, 0);
        	//xColor.glColor();
            GL11.glVertex3f(1, 1, 0);
            xyColor.glColor();
            GL11.glVertex3f(1, 1, 1);
            //xyColor.glColor();
            GL11.glVertex3f(1, 0, 1);
        }
        GL11.glEnd();
    }

    public static void renderColoredRightFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	xColor.glColor();
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(x, 0, 0);

        	//xColor.glColor();
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(x, y, 0);

        	xyColor.glColor();
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, x);

        	//xyColor.glColor();
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(x, 0, x);
        }
        GL11.glEnd();
    }

    public static void renderLeftFace() {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex3f(0, 0, 0);
            GL11.glVertex3f(0, 0, 1);
            GL11.glVertex3f(0, 1, 1);
            GL11.glVertex3f(0, 1, 0);
        }
        GL11.glEnd();
    }

    public static void renderLeftFaceTex(float x, float y) {
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(0, 0, 0);
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(0, 0, x);
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(0, y, x);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(0, y, 0);
        }
        GL11.glEnd();
    }

    public static void renderColoredLeftFace(Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glVertex3f(0, 0, 0);
        	yColor.glColor();
            GL11.glVertex3f(0, 0, 1);
        	//yColor.glColor();
            GL11.glVertex3f(0, 1, 1);
        	color.glColor();
            GL11.glVertex3f(0, 1, 0);
        }
        GL11.glEnd();
    }

    public static void renderColoredLeftFaceTex(float x, float y, Color color, Color xColor, Color yColor, Color xyColor) {
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color.glColor();
            GL11.glTexCoord2f(0, y);
            GL11.glVertex3f(0, 0, 0);

        	yColor.glColor();
            GL11.glTexCoord2f(x, y);
            GL11.glVertex3f(0, 0, x);

        	//yColor.glColor();
            GL11.glTexCoord2f(x, 0);
            GL11.glVertex3f(0, y, x);

        	color.glColor();
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(0, y, 0);
        }
        GL11.glEnd();
    }
}
