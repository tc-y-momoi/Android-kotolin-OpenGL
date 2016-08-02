package com.example.yujimomoi.opengl001

import java.util.Timer
import java.util.TimerTask

import android.app.Activity
import android.media.AudioManager
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class MainActivity : Activity() {

	private var myRenderer: MyRenderer? = null
	private var glSurfaceView: GLSurfaceView? = null
	// ハンドラを生成
	private val handler = Handler()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// フルスクリーン、タイトルバーの非表示
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		volumeControlStream = AudioManager.STREAM_MUSIC

		myRenderer = MyRenderer(this) // MyRendererの生成
		glSurfaceView = GLSurfaceView(this) // GLSurfaceViewの生成
		glSurfaceView!!.setRenderer(myRenderer) // GLSurfaceViewにMyRendererを適用

		// requestRender()が呼ばれた時だけMyRendereクラスのonDrawFrameメソッドを実行するようにするように設定変更
		// これなしの場合は、onDrawメソッドが定期的に実行される
		glSurfaceView!!.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
		setContentView(glSurfaceView) // ビューにGLSurfaceViewを指定

		// MyRendererのonDrawFrameメソッドを呼ぶためにタイマーを実装
		val timer = Timer(false)
		// スケジュールを設定
		timer.schedule(object : TimerTask() {
			override fun run() {
				handler.post {
					// MyRendererのonDrawFrameメソッドを呼ぶ
					glSurfaceView!!.requestRender()
				}
			}
		}, 1000, TIMER_PERIOD.toLong()) // 初回起動の遅延(1sec)と周期(TIMER_PERIOD)の指定
	}

	override fun onResume() {
		super.onResume()

		// GLSurfaceViewをonResumeにする
		glSurfaceView!!.onResume()
	}

	override fun onPause() {
		super.onPause()

		// GLSurfaceViewをonPauseにする
		glSurfaceView!!.onPause()
	}

	companion object {
		val TIMER_PERIOD = 20
	}

}