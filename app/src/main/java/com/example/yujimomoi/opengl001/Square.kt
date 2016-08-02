package com.example.yujimomoi.opengl001

import javax.microedition.khronos.opengles.GL10

/**
 * Created by yuji.momoi on 2016/07/29.
 */
class Square(texture_name:Int, x:Float, y:Float, z:Float) : BasaObject(0, x, y, z) {
	private var texture_name_: Int = texture_name
	private var texture_id_: Int? = 0

	companion object {
		fun create(texture_name:Int):Square = Square(texture_name, 0.0f, 0.0f, 0.0f)
		fun create(texture_name:Int,x:Float, y:Float, z:Float):Square = Square(texture_name, x, y, z)
		fun create(texture_name:Int,vec3:Vector3):Square = Square(texture_name, vec3.x, vec3.y, vec3.z)
	}

	fun init() {
		position_.setter(0.0f, 0.0f, 0.0f)
		rotation_.setter(0.0f, 0.0f, 0.0f)
		size_.setter(1.0f, 1.0f, 1.0f)
		scale_.setter(1.0f, 1.0f, 1.0f)
		color_.setter(1.0f, 1.0f, 1.0f, 1.0f)
	}

	fun init(posX:Float, posY:Float, posZ:Float, rotX:Float, rotY:Float, rotZ:Float,
			 sizeX:Float, sizeY:Float, sizeZ:Float) {
		position_.setter(posX, posY, posZ)
		rotation_.setter(rotX, rotY, rotZ)
		size_.setter(sizeX, sizeY, sizeZ)
		scale_.setter(1.0f, 1.0f, 1.0f)
		color_.setter(1.0f, 1.0f, 1.0f, 1.0f)
	}

	fun update() {
		//rotation_.x += 1f
	}

	fun draw(gl: GL10) {
		gl.glPushMatrix()
		run {
			gl.glTranslatef(position_.x, position_.y + size_.y / 2.0f, position_.z)
			gl.glRotatef(rotation_.x, 1.0f, 0.0f, 0.0f)
			gl.glRotatef(rotation_.y, 0.0f, 0.0f, 1.0f)
			gl.glRotatef(rotation_.z, 0.0f, 1.0f, 0.0f)
			GraphicUtil.drawTexture(gl, 0.0f, 0.0f, size_.x, size_.y, texture_id_.toString().toInt(),
			0.0f, 0.0f, scale_.x, scale_.y,
			color_.red, color_.green, color_.blue, color_.alpha)
		}
		gl.glPopMatrix()

		gl.glPushMatrix()
		run {
			gl.glTranslatef(position_.x, position_.y, position_.z + size_.z / 2.0f)
			gl.glRotatef(rotation_.x + 90.0f, 1.0f, 0.0f, 0.0f)
			gl.glRotatef(rotation_.y, 0.0f, 0.0f, 1.0f)
			gl.glRotatef(rotation_.z, 0.0f, 1.0f, 0.0f)
			GraphicUtil.drawTexture(gl, 0.0f, 0.0f, size_.x, size_.y, texture_id_.toString().toInt(),
			0.0f, 0.0f, scale_.x, scale_.y,
			color_.red, color_.green, color_.blue, color_.alpha)
		}
		gl.glPopMatrix()
	}

	fun setTexture(gl: GL10, width: Int, height: Int) {
		texture_id_ = TextureManager.getTextureId(texture_name_);
	}
}