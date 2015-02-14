package com.ralitski.maze.block;

import com.ralitski.maze.LoadedTextureHolder;
import com.ralitski.util.render.img.ImageList;
import com.ralitski.util.render.img.Texture;

public class Materials {
	
	//TODO:
	//-start and finish blocks
	//-test TrueTypeFont in 3D (for floating text above start / finish)
	
	private static final LoadedTextureHolder WALL_HOLDER = new LoadedTextureHolder();
	
	public static final Material EMPTY = new MaterialAir(0);
	public static final Material FLOOR = new MaterialFloor(1);
	public static final Material WALL = new MaterialTextured(2, WALL_HOLDER);
	
	public static void init(ImageList images) {
		Texture t = images.getImage("wall", true);
        t.noBlend();
		WALL_HOLDER.setTexture(t);
	}
}