package com.example.yujimomoi.opengl001

import android.content.res.Resources
import android.util.Log
import java.util.*
import javax.microedition.khronos.opengles.GL10

/**
 * Created by yuji.momoi on 2016/07/27.
 */
class TextureManager {

	companion object {
		private var texture_name_list_: Array<Int> = arrayOf(R.drawable._app_icon_dameo, R.drawable._floor, R.drawable.field004, R.drawable.mounten001)
		private var texture_id_list_: Array<Int?> = kotlin.arrayOfNulls<Int>(texture_name_list_.size)

		fun loadTexture(gl: GL10, res: Resources) {
			var cnt: Int = 0
			while(cnt < texture_name_list_.size) {
				var id: Int = GraphicUtil.loadTexture(gl, res, texture_name_list_[cnt])
				texture_id_list_[cnt] = id
				cnt ++
			}
		}

		fun getTextureId(texture_name: Int):Int? {
			var cnt: Int = 0
			while(cnt < texture_name_list_.size) {
				if(texture_name_list_[cnt] == texture_name) {
					return texture_id_list_[cnt]
				}
				cnt ++
			}

			return -1
		}
	}
}