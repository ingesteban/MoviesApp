package dev.esteban.test.presentation.utils

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.esteban.test.R
import java.util.*

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
abstract class BaseActivity: AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initializeView()
    }

    /**
     * Use este metodo para inicializar los componentes de la vista
     */
    protected abstract fun getLayoutId(): Int


    abstract fun initializeView()

    /**
     * Muestra un mensaje para notificar el resultado de una operacion
     */
    fun showMessageInformation(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }



}