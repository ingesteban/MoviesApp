package dev.esteban.test.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import dev.esteban.test.R
import dev.esteban.test.presentation.movies.MoviesActivity

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mHandler = Handler(mainLooper)
        mHandler.postDelayed(Runnable {
            //val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            //val sesionManager = SesionManager(prefs)

            //if (sesionManager.getTokeAccess() != null && !sesionManager.getTokeAccess().trim().isEmpty()) {
                val intent = Intent()
                intent.setClass(applicationContext, MoviesActivity::class.java)
                startActivity(intent)
            //} else {
            //    val intent = Intent()
            //    intent.setClass(applicationContext, LoginActivity::class.java!!)
            //    startActivity(intent)
            //}
        }, 3000)
    }

}
