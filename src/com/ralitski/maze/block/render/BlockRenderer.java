package com.ralitski.maze.block.render;

import com.ralitski.maze.block.Material;
import com.ralitski.maze.world.Level;

/**
 *
 * @author ralitski
 */
public interface BlockRenderer<M extends Material> {

    /**
     * Render a block into the world. Translations have already been done to
     * move this block into rendering place; coordinates are given only for data
     * checking.
     *
     * @param x the x coord of this block in the given level
     * @param y the y (z) coord of this block in the given level
     * @param level the level this block is in
     * @param mat the material this block is made of (for convenience)
     */
    void render(int x, int y, Level level, M mat);
}
