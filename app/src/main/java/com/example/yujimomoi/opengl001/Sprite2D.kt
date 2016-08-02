package com.example.yujimomoi.opengl001

import android.util.Log
import javax.microedition.khronos.opengles.GL10

/**
 * Created by yuji.momoi on 2016/07/27.
 */
class Sprite2D(texture_name: Int) {
	private var texture_name_: Int = texture_name
	private var texture_id_: Int? = 0
	private var position_: Vector3 = Vector3.create()
	private var rotation_: Vector3 = Vector3.create()
	private var size_: Vector2 = Vector2.create(1.0f, 1.0f)
	private var scale_: Vector2 = Vector2.create(1.0f, 1.0f)
	private var color_: Color4F = Color4F.create(1.0f, 1.0f, 1.0f, 1.0f)

	fun update() {
		rotation_.y += 0.1f
	}

	fun draw(gl: GL10) {
		gl.glPushMatrix()
		gl.glEnable(GL10.GL_CULL_FACE)
		run {
			gl.glTranslatef(position_.x, position_.y, position_.z)
			gl.glRotatef(rotation_.x, 1.0f, 0.0f, 0.0f)
			gl.glRotatef(rotation_.y, 0.0f, 0.0f, 1.0f)
			gl.glRotatef(rotation_.z, 0.0f, 1.0f, 0.0f)

			GraphicUtil.drawTexture(gl, 0.0f, 0.0f, size_.x, size_.y, texture_id_.toString().toInt(),
										0.0f, 0.0f, scale_.x, scale_.y,
										color_.red, color_.green, color_.blue, color_.alpha)
		}
		gl.glDisable(GL10.GL_CULL_FACE)
		gl.glPopMatrix()
	}

	fun setTexture(gl: GL10, width: Int, height: Int) {
		texture_id_ = TextureManager.getTextureId(texture_name_);
	}

	fun setPosition(x:Float, y:Float, z:Float) {
		this.position_.x = x
		this.position_.y = y
		this.position_.z = z
	}

	fun setPosition(vec3:Vector3) {
		this.position_.x = vec3.x
		this.position_.y = vec3.y
		this.position_.z = vec3.z
	}

	fun setRotation(x:Float, y:Float, z:Float) {
		this.rotation_.x = x
		this.rotation_.y = y
		this.rotation_.z = z
	}

	fun setRotation(vec3:Vector3) {
		this.rotation_.x = vec3.x
		this.rotation_.y = vec3.y
		this.rotation_.z = vec3.z
	}

	fun setSize(x:Float, y:Float) {
		this.size_.x = x
		this.size_.y = y
	}

	fun setSize(vec2:Vector2) {
		this.size_.x = vec2.x
		this.size_.y = vec2.y
	}

	fun setScale(x:Float, y:Float) {
		this.scale_.x = x
		this.scale_.y = y
	}

	fun setScale(vec2:Vector2) {
		this.scale_.x = vec2.x
		this.scale_.y = vec2.y
	}

	fun setColor(r:Float, g:Float, b:Float) {
		this.color_.setter(r, g, b)
	}

	fun setColor(r:Float, g:Float, b:Float, a:Float) {
		this.color_.setter(r, g, b, a)
	}

	fun setColor(color:Color4F) {
		this.color_.setter(color)
	}
}