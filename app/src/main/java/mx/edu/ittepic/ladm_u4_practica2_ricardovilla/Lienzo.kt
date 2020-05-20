package mx.edu.ittepic.ladm_u4_practica2_ricardovilla

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService

class Lienzo(p:MainActivity) : View(p) {

    var puntero = p
    var miLienzo : Lienzo ?= null
    var x1 = 200f
    var y1 = 700f
    var ryu_idle = FiguraGeometrica(BitmapFactory.decodeResource(resources, R.drawable.ryu_idl),x1.toInt(),y1.toInt())
    var ryu_power = FiguraGeometrica(BitmapFactory.decodeResource(resources, R.drawable.ryu_h),x1.toInt(),y1.toInt())

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()

       canvas.drawColor(Color.BLACK)
       // moverObjeto(nx,ny)

        if(puntero.cambio){

            canvas.drawColor(Color.WHITE)
            ryu_power = FiguraGeometrica(BitmapFactory.decodeResource(resources, R.drawable.ryu_h),x1.toInt(),y1.toInt())
            ryu_power.pintar(canvas,paint)
            //puntero.setTitle("Shoryuken!")
            //paint.color = Color.rgb(12, 92, 133)
            invalidate()
        }
        else {
            ryu_idle = FiguraGeometrica(BitmapFactory.decodeResource(resources, R.drawable.ryu_idl),x1.toInt(),y1.toInt())
            ryu_idle.pintar(canvas,paint)
            //ryu_power = FiguraGeometrica(BitmapFactory.decodeResource(resources, R.drawable.ryu_tats),x1.toInt(),y1.toInt())
            //ryu_power.pintar(canvas,paint)
            //paint.color = Color.RED
        }
        //canvas.drawCircle(x1,y1.toFloat(),50f,paint)


    }


    fun moverObjeto(x:Float) {
        x1 +=x

        if(x1<=-50 || x1>=width-150){
            x1*=-1
        }
        //y1 += y
        invalidate()
    }

}
