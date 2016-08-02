package com.example.yujimomoi.opengl001

import javax.microedition.khronos.opengles.GL10

/**
 * Created by yuji.momoi on 2016/07/28.
 */
class Camera(x:Float, y:Float, z:Float) {
	private var position_: Vector3 = Vector3.create(x, y, z)
	private var rotation_: Vector3 = Vector3.create()

	companion object {
		fun create():Camera = Camera(0.0f, 0.0f, 0.0f)
		fun create(x:Float, y:Float, z:Float):Camera = Camera(x, y, z)
		fun create(vec3:Vector3):Camera = Camera(vec3.x, vec3.y, vec3.z)
	}

	fun setCamera(gl: GL10) {
		gl.glTranslatef(position_.x, position_.y, position_.z)
		gl.glRotatef(rotation_.x, 1.0f, 0.0f, 0.0f)
		gl.glRotatef(rotation_.y, 0.0f, 1.0f, 0.0f)
		gl.glRotatef(rotation_.z, 0.0f, 0.0f, 1.0f)
	}

	fun setPosition(x:Float, y:Float, z:Float) {
		this.position_.x = x
		this.position_.y = y
		this.position_.z = z
	}

	fun setPosition(vec3:Vector3) {
		this.setPosition(vec3.x, vec3.y, vec3.z)
	}

	fun setRotation(x:Float, y:Float, z:Float) {
		this.rotation_.x = x
		this.rotation_.y = y
		this.rotation_.z = z
	}

	fun setRotation(vec3:Vector3) {
		this.setRotation(vec3.x, vec3.y, vec3.z)
	}

	fun getRotation(): Vector3 = this.rotation_
}