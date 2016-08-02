package com.example.yujimomoi.opengl001

/**
 * Created by yuji.momoi on 2016/07/28.
 */
class Vector3(x:Float, y:Float, z:Float) {
	public var x:Float = x
	public var y:Float = y
	public var z:Float = z

	public fun setter(x:Float, y:Float, z:Float) {
		this.x = x
		this.y = y
		this.z = z
	}

	public fun setter(vec3:Vector3) {
		this.x = vec3.x
		this.y = vec3.y
		this.z = vec3.z
	}

	public fun setterX(x:Float) {
		this.x = x
	}

	public fun setterX(vec3:Vector3) {
		this.x = vec3.x
	}

	public fun setterY(y:Float) {
		this.y = y
	}

	public fun setterY(vec3:Vector3) {
		this.y = vec3.y
	}

	public fun setterZ(z:Float) {
		this.z = z
	}

	public fun setterZ(vec3:Vector3) {
		this.z = vec3.z
	}

	companion object {
		public fun create():Vector3 = Vector3(0.0f, 0.0f, 0.0f)
		public fun create(x:Float, y:Float, z:Float):Vector3 = Vector3(x, y, z)
		public fun create(vec3:Vector3):Vector3 = Vector3(vec3.x, vec3.y, vec3.z)
	}
}