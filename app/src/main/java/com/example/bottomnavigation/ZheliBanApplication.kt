package com.example.bottomnavigation

import android.app.Application
import android.os.Environment
import com.kongzue.dialogx.DialogX
import tech.gujin.toast.ToastUtil

class ZheliBanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ToastUtil.initialize(this)
        DialogX.init(this)
        BASE_FACE_PATH = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()

        DIR_11_VALUE = "/11";
        DIR_1N_VALUE = "/1n";
        FACE_DIR_KEY="FACE_DIR_KEY"

        USER_ID_KEY="USER_ID_KEY"

    }

    public companion object {
        lateinit var  BASE_FACE_PATH: String

        lateinit var  DIR_11_VALUE: String
        lateinit var  DIR_1N_VALUE: String
        lateinit var  FACE_DIR_KEY: String

        lateinit var  USER_ID_KEY: String

    }
}