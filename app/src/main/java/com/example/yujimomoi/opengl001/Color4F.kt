package com.example.yujimomoi.opengl001

/**
 * Created by yuji.momoi on 2016/07/28.
 */
class Color4F(r:Float, g:Float, b:Float, a:Float) {
	public var red:Float = r
	public var green:Float = g
	public var blue:Float = b
	public var alpha:Float = a

	public fun setter(r:Float, g:Float, b:Float) {
		this.red = r
		this.green = g
		this.blue = b
	}

	public fun setter(r:Float, g:Float, b:Float, a:Float) {
		this.red = r
		this.green = g
		this.blue = b
		this.alpha = a
	}

	public fun setter(color:Color4F) {
		this.red = color.red
		this.green = color.green
		this.blue = color.blue
		this.alpha = color.alpha
	}

	public fun setterRed(r:Float) {
		this.red = r
	}

	public fun setterRed(color:Color4F) {
		this.red = color.red
	}

	public fun setterGreen(g:Float) {
		this.green = g
	}

	public fun setterGreen(color:Color4F) {
		this.green = color.green
	}

	public fun setterBlue(b:Float) {
		this.blue = b
	}

	public fun setterBlue(color:Color4F) {
		this.blue = color.blue
	}

	public fun setterAlpha(a:Float) {
		this.alpha = a
	}

	public fun setterAlpha(color:Color4F) {
		this.alpha = color.alpha
	}

	companion object {
		public fun create():Color4F = Color4F(0.0f, 0.0f, 0.0f, 0.0f)
		public fun create(r:Float, g:Float, b:Float, a:Float):Color4F = Color4F(r, g, b, a)
		public fun create(color:Color4F):Color4F = Color4F(color.red, color.green, color.blue, color.alpha)
	}
}