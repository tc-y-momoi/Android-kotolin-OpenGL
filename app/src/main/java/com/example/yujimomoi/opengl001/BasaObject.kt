package com.example.yujimomoi.opengl001

/**
 * Created by yuji.momoi on 2016/07/29.
 */
open class BasaObject(obj_type:Int, x:Float, y:Float, z:Float) {
	protected var position_: Vector3 = Vector3.create(x, y, z)
	protected var rotation_: Vector3 = Vector3.create()
	protected var size_: Vector3 = Vector3.create()
	protected var scale_: Vector3 = Vector3.create()
	protected var color_: Color4F = Color4F.create()
	var obj_type_: Int = obj_type

	fun setPosition(x:Float, y:Float, z:Float) {
		this.position_.x = x
		this.position_.y = y
		this.position_.z = z
	}

	fun setPosition(vec3:Vector3) {
		this.setPosition(vec3.x, vec3.y, vec3.z)
	}

	fun getPosition():Vector3 = this.position_

	fun setRotation(x:Float, y:Float, z:Float) {
		this.rotation_.x = x
		this.rotation_.y = y
		this.rotation_.z = z
	}

	fun setRotation(vec3:Vector3) {
		this.setRotation(vec3.x, vec3.y, vec3.z)
	}

	fun getRotation():Vector3 = this.rotation_

	fun setSize(x:Float, y:Float) {
		this.size_.x = x
		this.size_.y = y
	}

	fun setSize(x:Float, y:Float, z:Float) {
		this.size_.x = x
		this.size_.y = y
		this.size_.z = z
	}

	fun setSize(vec3:Vector3) {
		this.setSize(vec3.x, vec3.y, vec3.z)
	}

	fun getSize():Vector3 = this.size_

	fun setScale(x:Float, y:Float, z:Float) {
		this.scale_.x = x
		this.scale_.y = y
		this.scale_.z = z
	}

	fun setScale(vec3:Vector3) {
		this.setScale(vec3.x, vec3.y, vec3.z)
	}

	fun getScale():Vector3 = this.scale_

	fun setColor(r:Float, g:Float, b:Float) {
		this.color_.setter(r, g, b)
	}

	fun setColor(r:Float, g:Float, b:Float, a:Float) {
		this.color_.setter(r, g, b, a)
	}

	fun setColor(color:Color4F) {
		this.color_.setter(color)
	}

	fun getColor():Color4F = this.color_

	fun getObjType():Int = this.obj_type_
}