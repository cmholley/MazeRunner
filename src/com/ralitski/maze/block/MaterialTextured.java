package com.ralitski.maze.block;

import com.ralitski.maze.block.render.BlockRenderer;
import com.ralitski.maze.block.render.BlockRenderers;
import com.ralitski.util.render.img.Texture;

public class MaterialTextured extends Material {
	
	private TextureHolder texture;

	public MaterialTextured(int id) {
		super(id);
	}

	public MaterialTextured(int id, TextureHolder texture) {
		super(id);
		this.texture = texture;
	}

	public boolean isTextured(int data, BlockFace face) {
		return texture != null ? texture.isTextured(data, face) : false;
	}

	public Texture getTexture(int data, BlockFace face) {
		//null check is done already
		return texture.getTexture(data, face);
	}

	public boolean isTextured(int data) {
		return texture != null ? texture.isTextured(data) : false;
	}

	public Texture getTexture(int data) {
		//null check is done already
		return texture.getTexture(data);
	}

	public interface TextureHolder {
		boolean isTextured(int data, BlockFace face);
		Texture getTexture(int data, BlockFace face);
		boolean isTextured(int data);
		Texture getTexture(int data);
	}

}
