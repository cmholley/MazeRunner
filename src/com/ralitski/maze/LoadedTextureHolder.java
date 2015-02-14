package com.ralitski.maze;

import com.ralitski.maze.block.BlockFace;
import com.ralitski.maze.block.MaterialTextured.TextureHolder;
import com.ralitski.util.render.img.Texture;

public class LoadedTextureHolder implements TextureHolder {
	
	private Texture texture;
	
	public LoadedTextureHolder() {
		this(null);
	}
	
	public LoadedTextureHolder(Texture texture) {
		this.texture = texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public boolean isTextured(int data, BlockFace face) {
		return texture != null;
	}

	@Override
	public Texture getTexture(int data, BlockFace face) {
		return texture;
	}

	@Override
	public boolean isTextured(int data) {
		return texture != null;
	}

	@Override
	public Texture getTexture(int data) {
		return texture;
	}

}
