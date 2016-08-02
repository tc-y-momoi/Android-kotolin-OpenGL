package com.example.yujimomoi.opengl001

/**
 * Created by yuji.momoi on 2016/07/28.
 */
class Vector2(x:Float, y:Float) {
	public var x:Float = x
	public var y:Float = y

	public fun setter(x:Float, y:Float) {
		this.x = x
		this.y = y
	}

	public fun setter(vec2:Vector2) {
		this.x = vec2.x
		this.y = vec2.y
	}

	public fun setterX(x:Float) {
		this.x = x
	}

	public fun setterX(vec2:Vector2) {
		this.x = vec2.x
	}

	public fun setterY(y:Float) {
		this.y = y
	}

	public fun setterY(vec2:Vector2) {
		this.y = vec2.y
	}

	companion object {
		public fun create():Vector2 = Vector2(0.0f, 0.0f)
		public fun create(x:Float, y:Float):Vector2 = Vector2(x, y)
		public fun create(vec2:Vector2):Vector2 = Vector2(vec2.x, vec2.y)
	}
}