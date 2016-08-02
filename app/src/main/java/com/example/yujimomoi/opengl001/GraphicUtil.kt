package com.example.yujimomoi.opengl001

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

import javax.microedition.khronos.opengles.GL10

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Bitmap.Config
import android.opengl.GLUtils
import android.util.Log

object GraphicUtil {

	// テクスチャをロードするためのメソッド
	fun loadTexture(gl: GL10, resources: Resources, resId: Int): Int {
		val textures = IntArray(1)

		// Bitmapの作成
		val bmp = BitmapFactory.decodeResource(resources, resId, options) ?: return 0

		// OpenGL用のテクスチャを生成します
		gl.glGenTextures(1, textures, 0)
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0])
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0)
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR.toFloat())
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR.toFloat())
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0)

		// OpenGLへの転送が完了したので、VMメモリ上に作成したBitmapを破棄する
		bmp.recycle()

		return textures[0]
	}

	private val options = BitmapFactory.Options()

	init {
		options.inScaled = false// リソースの自動リサイズをしない
		options.inPreferredConfig = Config.ARGB_8888// 32bit画像として読み込む
	}

	// テクスチャを描画するためのメソッド
	fun drawTexture(gl: GL10, x: Float, y: Float,
					width: Float, height: Float, texture: Int, u: Float, v: Float,
					tex_w: Float, tex_h: Float, r: Float, g: Float, b: Float, a: Float) {

		// ポリゴンの頂点座標
		val vertices = floatArrayOf(-0.5f * width + x, -0.5f * height + y, 0.5f * width + x, -0.5f * height + y, -0.5f * width + x, 0.5f * height + y, 0.5f * width + x, 0.5f * height + y)

		//　ポリゴンの頂点の色
		val colors = floatArrayOf(r, g, b, a, r, g, b, a, r, g, b, a, r, g, b, a)

		// マッピング座標
		val coords = floatArrayOf(u, v + tex_h, u + tex_w, v + tex_h, u, v, u + tex_w, v)

		// OpenGLではVM上に確保したメモリ領域にアクセスできないため、
		// 作成した配列をシステムメモリに転送する必要がある。
		val polygonVertices = makeFloatBuffer(vertices)
		val polygonColors = makeFloatBuffer(colors)
		val texCoords = makeFloatBuffer(coords)

		gl.glEnable(GL10.GL_TEXTURE_2D) // テクスチャ機能の有効化
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture) // テクスチャオブジェクトの指定(引数で取得する)

		// glVertexPointer(1頂点あたりのデータ数, データ型, オフセット, 頂点配列)
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, polygonVertices) // 確保したメモリをOpenGLに渡す
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY) // ポリゴン頂点座標のバッファをセットしたことをOpenGLに伝える
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, polygonColors) // 確保したメモリをOpenGLに渡す
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY) // ポリゴン頂点色のバッファをセットしたことをOpenGLに伝える
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords) // 確保したメモリをOpenGLに渡す
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY) // マッピング座標のバッファをセットしたことをOpenGLに伝える

		// ポリゴンの描画には幾つか種類があり、引数で指定(GL10.GL_TRIANGLE_STRIP)
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4) // ポリゴンの描画

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY) // 描画が終わったら、テクスチャマッピング用のバッファをリセット
		gl.glDisable(GL10.GL_TEXTURE_2D) // テクスチャ機能の無効化
	}

	// システム上のメモリ領域を確保するためのメソッド
	// float型の配列を入れるためのデータ領域を用意し、そこにデータを転送している。
	// 配列のサイズは[4 * 配列のサイズ]バイトとなっている。
	fun makeFloatBuffer(arr: FloatArray): FloatBuffer {
		val bb = ByteBuffer.allocateDirect(arr.size * 4)
		bb.order(ByteOrder.nativeOrder())
		val fb = bb.asFloatBuffer()
		fb.put(arr)
		fb.position(0)
		return fb
	}
}