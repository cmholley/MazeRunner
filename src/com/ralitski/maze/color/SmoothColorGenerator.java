package com.ralitski.maze.color;

import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.render.img.Color;

public class SmoothColorGenerator implements ColorGenerator {

	private float xScale;
	private float yScale;
	private float size;
	private Point2d center;
	private Color start;
	private Color end;
	
	public SmoothColorGenerator() {
		xScale = yScale = 1;
		center = Point2d.ORIGIN;
	}
	
	public SmoothColorGenerator setCenter(float x, float y) {
		return setCenter(new Point2d(x, y));
	}
	
	public SmoothColorGenerator setCenter(Point2d p) {
		center = p;
		return this;
	}
	
	public SmoothColorGenerator setScaleX(float scale) {
		xScale = scale;
		return this;
	}
	
	public SmoothColorGenerator setScaleY(float scale) {
		yScale = scale;
		return this;
	}
	
	public SmoothColorGenerator setSize(float size) {
		this.size = size;
		return this;
	}
	
	public SmoothColorGenerator setColorStart(Color c) {
		this.start = c;
		return this;
	}
	
	public SmoothColorGenerator setColorEnd(Color c) {
		this.end = c;
		return this;
	}

	@Override
	public Color getColor(int x, int y) {
		Point2d p = new Point2d((float)x * xScale, (float)y * yScale);
		//raw function input; get it in terms of color ratio
		float f = p.length(center);
		//add one to make sure it keeps a proper range (eg, 0-40 instead of 0-39)
		f %= size * 2 + 1;
		f = Math.abs(size - f);
		f /= size;
		float f2 = 1F - f;
		float red = (f * start.getRedFloat()) + (f2 * end.getRedFloat());
		float green = (f * start.getGreenFloat()) + (f2 * end.getGreenFloat());
		float blue = (f * start.getBlueFloat()) + (f2 * end.getBlueFloat());
		return new Color(red, green, blue);
	}

}
