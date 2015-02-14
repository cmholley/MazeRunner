package com.ralitski.maze.world;

import java.util.Arrays;
import java.util.Iterator;

import com.foodyling.util.SparseArray;
import com.ralitski.util.ArrayUtils;

public class ChunkStorageImpl implements ChunkStorage {
	
	private SparseArray<Chunk> chunks;
	
	public ChunkStorageImpl() {
		chunks = new SparseArray<>(2);
	}
	
	private int shift(int i) {
		//get rid of negatives
		return (i * 2) >>> 1;
	}
	
	private int[] coords(int x, int y) {
		int[] i = new int[]{shift(x), shift(y)};
		//System.out.println(x + " " + y + " " + Arrays.toString(i));
		return i;
	}

	@Override
	public void setChunkAt(int x, int y, Chunk c) {
		if(c != null) {
			if(c.getX() == x && c.getY() == y) {
				chunks.addItem(coords(x, y), c);
			} else {
				throw new IllegalArgumentException("Can't set " + c + " at (" + x + ", " + ")");
			}
		} else {
			chunks.removeItem(coords(x, y));
		}
	}

	@Override
	public Chunk getChunkAt(int x, int y) {
		return chunks.getItem(coords(x, y));
	}

	@Override
	public Iterator<Chunk> iterator() {
		return chunks.iterator();
	}
	
//	private Chunk chunk;
//	
//	@Override
//	public void setChunkAt(int x, int y, Chunk c) {
//		chunk = c;
//	}
//
//	@Override
//	public Chunk getChunkAt(int x, int y) {
//		return chunk;
//	}
//
//	@Override
//	public Iterator<Chunk> iterator() {
//		return ArrayUtils.getIterator(chunk);
//	}
	
//	private class ChunkIter implements Iterator<Chunk> {
//		
//		private int index;
//		private Chunk next;
//		
//		private ChunkIter() {
//			getNext();
//		}
//
//		@Override
//		public boolean hasNext() {
//			return next != null;
//		}
//
//		@Override
//		public Chunk next() {
//			Chunk c = next;
//			getNext();
//			return c;
//		}
//
//		@Override
//		public void remove() {
//		}
//		
//		private void getNext() {
//			next = null;
//			while(next == null && index < chunks.length) {
//				next = chunks[index++];
//			}
//		}
//	}

}
