package com.ralitski.maze.world.render;

import java.util.logging.Logger;

import com.ralitski.maze.world.Chunk;
import com.ralitski.maze.world.Level;
import com.ralitski.util.render.list.ListMaker;

public class WorldRenderManager {
	
	private Level level;
	
	public void render(float partial) {
		for(Chunk c : level.getChunkStorage()) {
			RenderData data = getData(c);
			ListMaker list = getListMaker(c, data);
			if(c.isDirty()) {
				list.compile();
				c.setDirty(false);
			}
			list.call();
		}
	}

	public void setLevel(Level level) {
		if(this.level != null) {
			for(Chunk c : this.level.getChunkStorage()) {
				RenderData data = getData(c);
				//free dem memoriez
				data.getListMaker().delete();
			}
		}
		this.level = level;
		for(Chunk c : this.level.getChunkStorage()) {
			RenderData data = getData(c);
			ListMaker list = getListMaker(c, data);
			//when in doubt, recompile, don't register
			list.compile();
			//clean it up, otherwise it may be unnecessarily recompiled on the next render tick
			c.setDirty(false);
		}
	}
	
	private RenderData getData(Chunk c) {
		Object o = c.getCustomRenderData();
		if(o != null && o instanceof RenderData) {
			return (RenderData)o;
		} else {
			if(o != null) {
				Logger.getLogger(getClass().getName())
				.log(java.util.logging.Level.INFO, "Dumping chunk render data " + o);
			}
			RenderData r = new RenderData();
			c.setCustomRenderData(r);
			return r;
		}
	}
	
	private ListMaker getListMaker(Chunk c, RenderData data) {
		ListMaker list = data.getListMaker();
		if(list == null) {
			list = new ChunkRenderer(c);
			data.setListMaker(list);
		}
		return list;
	}

}
