package mx.edu.ittepic.ladm_u4_practica2_ricardovilla

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import mx.edu.ittepic.ladm_u4_practica2_ricardovilla.R

class FiguraGeometrica () {
    var x = 0f
    var y = 0f
    var tipo = 1 //1 circulo 2 rectangulo // 3 bitmap //4 text
    var radio = 0f
    var ancho = 0f
    var alto = 0f
    var incX = 5
    var incY = 5
    var bitmap :Bitmap ?=null
    var tamanoTexto = 0f
    var texto = ""

    constructor(x:Int, y:Int, radio:Int) : this(){
        this.x = x.toFloat()
        this.y = y.toFloat()
        this.radio = radio.toFloat()
        //////////////////////////////////////////////////
        ancho = this.radio*2
        alto = ancho
    }

    constructor(x:Int, y:Int, ancho:Int, alto:Int) : this(){
        this.x = x.toFloat()
        this.y = y.toFloat()
        this.ancho = ancho.toFloat()
        this.alto = alto.toFloat()
        tipo = 2
    }

    constructor(imagen:Bitmap,x:Int, y:Int) : this(){
        bitmap= imagen
        this.x = x.toFloat()
        this.y = y.toFloat()
        ancho = bitmap!!.width.toFloat()
        alto = bitmap!!.height.toFloat()
        tipo = 3

    }
    constructor(x:Int, y:Int,texto:String,tamanoTexto:Int) : this(){
        this.x = x.toFloat()
        this.y = y.toFloat()
        this.tamanoTexto = tamanoTexto.toFloat()
        this.texto = texto
        this.ancho = ancho.toFloat()
        this.alto = alto.toFloat()
        tipo = 4
    }
    fun pintar(c: Canvas, p: Paint){
        when(tipo){
            1->{
                //////////////////////////////////////////////////
                c.drawCircle(x + radio,y + radio,radio,p)
            }
            2->{
                c.drawRect(x,y,x+ancho,y+alto,p)
            }

            3->{
                c.drawBitmap(bitmap!!,x,y,p)
            }
            4->{

                p.setTextSize(tamanoTexto)
                c.drawText(texto,x,y,p)
            }
        }
    }

    fun estaEnArea(event: MotionEvent):Boolean{
        if(event.x >= x && event.x<=x+ancho){
            if(event.y >= y && event.y<=y+alto){
                return true
            }
        }
        return false
    }

    fun estaEnArea(posX : Float, posY : Float):Boolean{
        if(posX >= x && posX <= x + ancho){
            if(posY >= y && posY <= y + alto){
                return true
            }
        }
        return false
    }

    fun arrastrar(event: MotionEvent){
        x = event.x - (ancho/2)
        y = event.y - (alto/2)
    }

    fun rebote(ancho:Int, alto:Int){
        x+= incX
        if(x<=-100 || x>=ancho){
            incX *= -1
        }
        y+= incY
        if(y<=-100 || y>=alto){
            incY *= -1
        }
    }

    fun colision(objetoB:FiguraGeometrica) : Boolean {
        var x2 = x + ancho
        var y2 = y + alto

        //Caso 1
        if(objetoB.estaEnArea(x2, y2 + alto)){
            return true
        }

        //Caso 2
        if(objetoB.estaEnArea(x, y2)){
            return true
        }

        //Caso 3
        if(objetoB.estaEnArea(x2, y)){
            return true
        }

        //Caso 4
        if(objetoB.estaEnArea(x, y)){
            return true
        }

        return false
    }




}