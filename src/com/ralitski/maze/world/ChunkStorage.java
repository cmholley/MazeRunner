package com.ralitski.maze.world;

public interface ChunkStorage extends Iterable<Chunk> {
	
	//note: these methods take chunk coordinates(ie, world coordinates / Chunk.SIZE)

	void setChunkAt(int x, int y, Chunk c);
	Chunk getChunkAt(int x, int y);
}
