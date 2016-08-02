package com.example.yujimomoi.opengl001

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

import android.content.Context
import android.opengl.GLSurfaceView
import java.util.*

class MyRenderer(private val context: Context) : GLSurfaceView.Renderer {

	private val SCREEN_WIDTH:Float = 1080.0f
	private val SCREEN_HEIGHT:Float = 1920.0f
	private var width: Int = 0
	private var height: Int = 0

	// テクスチャ管理のためのID
	private var sideTex: Int = 0
	private var backTex: Int = 0
	private var camera_: Camera = Camera.create(2.0f, 0.0f, -3.0f)
	private var field_: Sprite2D = Sprite2D(R.drawable.field004)
	private var sprite2d_: Sprite2D = Sprite2D(R.drawable._app_icon_dameo)
	private var square_: Square = Square.create(R.drawable._floor)

	fun renderMain(gl: GL10) {

		// 3D描画用に座標系を設定する
		gl.glMatrixMode(GL10.GL_PROJECTION)
		gl.glLoadIdentity()
		gl.glFrustumf(-0.3f, 0.3f, -0.2f, 0.2f, 0.5f, 20.0f)
		gl.glMatrixMode(GL10.GL_MODELVIEW)
		gl.glLoadIdentity()

		// 視点を変更する
		// 全体を回転および移動させる
//		var camVec3: Vector3 = camera_.getRotation()
//		camera_.setRotation(camVec3.x, camVec3.y, camVec3.z)
		camera_.setCamera(gl)
//		gl.glTranslatef(0.0f, 0.0f, -1.0f) // 全体を奥方向に移動させる
//		gl.glRotatef(-70.0f, 1.0f, 0.0f, 0.0f) // x軸を中心に反時計回りに全体を70度回転させる

		gl.glEnable(GL10.GL_BLEND)// 背景を透明にするためブレンドを有効化
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA) // ブレンドの種類を設定

		// 地面描画
		field_.setSize(2.0f, 3.0f)
		field_.draw(gl)

//		gl.glPushMatrix()
//		run {
//			gl.glTranslatef(0.0f, 1.5f, 0.5f) // y軸方向に1.5f,z軸方向に0.5f移動させる
//			gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f) // x軸を中心に90度回転
//
//			// 奥背景の描画
//			GraphicUtil.drawTexture(gl, 0.0f, 0.0f, 2.0f, 1.0f, backTex, 0.0f,
//					0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f)
//		}
//		gl.glPopMatrix()
//
//		gl.glPushMatrix()
//		run {
//
//			gl.glTranslatef(1.0f, 0.0f, 0.5f) // x軸方向に1.0f移動させ、z軸方向に0.5f移動
//			gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f) // y軸中心に90度回転
//			gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f) // z軸中心に90度回転
//
//			// 右横背景の描画
//			GraphicUtil.drawTexture(gl, 0.0f, 0.0f, 3.0f, 1.0f, sideTex, 0.0f,
//					0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f)
//		}
//		gl.glPopMatrix()
//
//		gl.glPushMatrix()
//		run {
//
//			gl.glTranslatef(-1.0f, 0.0f, 0.5f) // x軸方向に-1.0f移動させ、z軸方向に0.5f移動
//			gl.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f) // y軸中心に-90度回転
//			gl.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f) // z軸中心に-90度回転
//
//			// 左横背景の描画
//			GraphicUtil.drawTexture(gl, 0.0f, 0.0f, 3.0f, 1.0f, sideTex, 0.0f,
//					0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f)
//		}
//		gl.glPopMatrix()

//		sprite2d_.setPosition(0.0f, 0.0f, 0.15f)
//		sprite2d_.update()
//		sprite2d_.setSize(0.3f, 0.3f)
//		sprite2d_.draw(gl)

		square_.update()
		square_.draw(gl)

		gl.glDisable(GL10.GL_BLEND) // GL_BLENDを無効にする
	}

	// 描画処理を記述する
	override fun onDrawFrame(gl: GL10) {

		// ビュー内部で実際に描画する範囲を指定
		gl.glViewport(0, 0, width, height)
		gl.glMatrixMode(GL10.GL_PROJECTION)
		gl.glLoadIdentity()

		// glOrthof(左端のx座標, 右端のx座標, 下端のy座標, 上端のy座標, 手前のz座標, 奥のz座標)
		gl.glOrthof(0.0f, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0f, 10.0f, -10.0f)
		gl.glMatrixMode(GL10.GL_MODELVIEW)
		gl.glLoadIdentity()

		// 画面をクリアする
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f)
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT)

		renderMain(gl)
	}

	// 画面生成時、画面向き変更時に呼ばれる。初期化処理などを行う。
	override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {

		var scale:Vector2 = Vector2.create((width / SCREEN_WIDTH).toFloat(),(height / SCREEN_HEIGHT).toFloat())
		var scl:Float = if(scale.x > scale.y) scale.y else scale.x
		var dp:Vector2 = Vector2.create(scl * SCREEN_WIDTH, scl * SCREEN_HEIGHT)
		var offset:Vector2 = Vector2.create((width - dp.x) / 2.0f, (height - dp.y) / 2.0f)
		gl.glViewport(offset.x.toInt(), offset.y.toInt(), dp.x.toInt(), dp.y.toInt())
		gl.glMatrixMode(GL10.GL_PROJECTION)
		gl.glLoadIdentity()
		gl.glOrthof(0.0f, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0f, 10.0f, -10.0f)
		// SurfaceViewのサイズを取得
		this.width = width
		this.height = height

		TextureManager.loadTexture(gl, context.resources)

		// テクスチャの画像を読み込む
		sideTex = GraphicUtil.loadTexture(gl, context.resources,
				R.drawable._floor)
		backTex = GraphicUtil.loadTexture(gl, context.resources,
				R.drawable.mounten001)

		camera_.setRotation(-70.0f, 0.0f, 0.0f)

		field_.setTexture(gl, width, height)
		//sprite2d_.setTexture(gl, width, height)
		square_.init()
		square_.setTexture(gl, width, height)
		square_.setPosition(0.0f, 0.0f, 0.0f)
		square_.setSize(1.0f, 1.0f)
	}

	// 画面生成時、画面向き変更時に呼ばれる。初期化処理などを行う。
	override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
	}
}