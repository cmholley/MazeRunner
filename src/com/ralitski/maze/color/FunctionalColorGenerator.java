package com.ralitski.maze.color;

import com.ralitski.util.math.geom.Geometry;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.func.CosFunction;
import com.ralitski.util.math.geom.d2.func.Function;
import com.ralitski.util.math.geom.d2.func.Line2d;
import com.ralitski.util.math.geom.d2.func.SinFunction;
import com.ralitski.util.math.geom.d2.func.FilterFunction.*;
import com.ralitski.util.render.img.Color;

public class FunctionalColorGenerator implements ColorGenerator {
	
	public static FunctionalColorGenerator gradient(Color start, Color end, float size) {
		float scale = Geometry.HALF_PI;
		size *= scale;
		size = 2F / size;
		return new FunctionalColorGenerator()
		.setRedFunction(getPeriodicFunction(start.getRedFloat(), end.getRedFloat()))
		.setGreenFunction(getPeriodicFunction(start.getGreenFloat(), end.getGreenFloat()))
		.setBlueFunction(getPeriodicFunction(start.getBlueFloat(), end.getBlueFloat()))
		.setRedScale(size)
		.setGreenScale(size)
		.setBlueScale(size)
		;
	}
	
	public static Function getPeriodicFunction(float start, float end) {
		float dif = end - start;
		//sin^2 and cos^2 are hardly blended. :I
		if(dif > 0) {
			//sin
			return new AdditiveFunction(new MultiplyingFunction(SinFunction.INSTANCE_2, dif), start);
			//return new MultiplyingFunction(new AdditiveFunction(SinFunction.INSTANCE, 1 + start), dif / 2F);
		} else if(dif < 0) {
			//cos
			return new AdditiveFunction(new MultiplyingFunction(CosFunction.INSTANCE_2, -dif), end);
			//return new MultiplyingFunction(new AdditiveFunction(CosFunction.INSTANCE, 1 + end), -dif / 2F);
		} else {
			//static
			return new Line2d(0, start);
		}
	}
	
	private Point2d center;
	private Function red, green, blue;
	private float redScale, greenScale, blueScale;
	
	public FunctionalColorGenerator() {
		redScale = greenScale = blueScale = 1;
		center = Point2d.ORIGIN;
	}
	
	public FunctionalColorGenerator setCenter(float x, float y) {
		return setCenter(new Point2d(x, y));
	}
	
	public FunctionalColorGenerator setCenter(Point2d p) {
		center = p;
		return this;
	}
	
	public FunctionalColorGenerator setRedFunction(Function f) {
		red = f;
		return this;
	}
	
	public FunctionalColorGenerator setGreenFunction(Function f) {
		green = f;
		return this;
	}
	
	public FunctionalColorGenerator setBlueFunction(Function f) {
		blue = f;
		return this;
	}
	
	public FunctionalColorGenerator setRedScale(float scale) {
		redScale = scale;
		return this;
	}
	
	public FunctionalColorGenerator setGreenScale(float scale) {
		greenScale = scale;
		return this;
	}
	
	public FunctionalColorGenerator setBlueScale(float scale) {
		blueScale = scale;
		return this;
	}

	@Override
	public Color getColor(int x, int y) {
		Point2d p = new Point2d(x, y);
		//raw function input
		float f = p.length(center);
		//scaled function inputs
		float fRed = (float)f * redScale;
		float fGreen = (float)f * greenScale;
		float fBlue = (float)f * blueScale;
		//function outputs
		float r = red.getY(fRed);
		float g = green.getY(fGreen);
		float b = blue.getY(fBlue);
		//le return
		return new Color(r, g, b);
	}
	
	
}
