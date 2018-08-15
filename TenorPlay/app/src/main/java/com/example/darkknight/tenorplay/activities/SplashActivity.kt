package com.example.darkknight.tenorplay.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.design.R.id.container
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.example.darkknight.tenorplay.R

class SplashActivity : AppCompatActivity() {
    var permissionsString = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,

            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val imageView1= findViewById<ImageView>(R.id.tenor_image)
        val imageView2= findViewById<ImageView>(R.id.music)
        val imageView3= findViewById<ImageView>(R.id.tenor)
        val imageView4= findViewById<ImageView>(R.id.play)
        val a1 = AnimationUtils.loadAnimation(this,R.anim.rotate)
        imageView1.startAnimation(a1)
        val a2 = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        imageView2.startAnimation(a2)
        val a3 = AnimationUtils.loadAnimation(this,R.anim.slide_right)
        imageView3.startAnimation(a3)
        val a4 = AnimationUtils.loadAnimation(this,R.anim.slide_left)
        imageView4.startAnimation(a4)

        if (!hasPermissions(this@SplashActivity, *permissionsString)) {

            ActivityCompat.requestPermissions(this@SplashActivity, permissionsString, 131)


        } else {
            handle()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            131 -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED
                ) {
                    handle()
                } else {

                    Toast.makeText(this@SplashActivity, "Please grant all the permissions to continue.",
                            Toast.LENGTH_SHORT).show()
                    this.finish()
                }
                return
            }
            else -> {
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                this.finish()
                return
            }
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        var hasAllPermissions = true
        for (permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermissions = false
            }
        }
        return hasAllPermissions
    }

    fun handle() {
        Handler().postDelayed({
            val startAct = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(startAct)
            this.finish()
        }, 1500)
    }
}
