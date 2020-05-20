package mx.edu.ittepic.ladm_u4_practica2_ricardovilla

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() , SensorEventListener{

    lateinit var sensorManager: SensorManager
    lateinit var sensorManager_Prox: SensorManager
    var miLienzo : Lienzo ?= null
    var cambio = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //ACELEROMETRO
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)




        //PROXIMIDAD
        sensorManager_Prox = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager_Prox.registerListener(this,
            sensorManager_Prox.getDefaultSensor(Sensor.TYPE_PROXIMITY),
            SensorManager.SENSOR_DELAY_NORMAL)



        miLienzo = Lienzo(this)
        setContentView(miLienzo)


    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
       // cambio = true

        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){

            miLienzo!!.moverObjeto((-event.values[0]))
        }


        if(event.sensor.type == Sensor.TYPE_PROXIMITY) {

            if (event.values[0] == 0f) {
                cambio = true
                miLienzo!!.invalidate()
            }
            else{
                cambio = false
            }


        }



    }






}

