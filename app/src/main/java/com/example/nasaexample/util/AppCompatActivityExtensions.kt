package com.example.nasaexample.util

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*


fun AppCompatActivity.replaceFragment(viewGroupId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(viewGroupId, fragment)
        .commit()
}

fun AppCompatActivity.replaceFragmentWithBackStack(viewGroupId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .addToBackStack(fragment.tag)
        .replace(viewGroupId, fragment)
        .commit()
}

fun AppCompatActivity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun AppCompatActivity.fullSreen(appCompatActivity: AppCompatActivity){
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(

        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN

    );
    /*window.decorView.apply {
        systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
    }*/
    hideNavigationBar(appCompatActivity)
}

fun setScreenBrightness(value:Int,appCompatActivity: AppCompatActivity){
    if (if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Settings.System.canWrite(appCompatActivity)
        } else {
           false// TODO("VERSION.SDK_INT < M")
        }
    ) {

        Settings.System.putInt(appCompatActivity.applicationContext.getContentResolver(),
            Settings.System.SCREEN_BRIGHTNESS,value
        )
        //Settings.System.putString(this.applicationContext.getContentResolver(),Settings.System.SCREEN_OFF_TIMEOUT,"-1")
        // change setting here
    } else { //Migrate to Setting write permission screen.
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.data = Uri.parse("package:" + appCompatActivity.applicationContext.packageName)
       // appCompatActivity.startActivity(intent) // TODO(
    }

}

fun hideNavigationBar(appCompatActivity: AppCompatActivity) {
    val decorView: View = appCompatActivity.window.decorView
    val uiOptions: Int = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            )
    val timer = Timer()
    val task: TimerTask = object : TimerTask() {
        override fun run() {
            appCompatActivity.runOnUiThread(Runnable {
                decorView.setSystemUiVisibility(
                    uiOptions
                )
            })
        }
    }
    timer.scheduleAtFixedRate(task, 1, 2)
}