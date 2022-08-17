package com.example.aula6kotlinmapas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object BitmapHelper {

    fun vectorToBitmap(
        context: Context,
        @DrawableRes id: Int,
        @ColorInt color: Int
    ) : BitmapDescriptor{

        val vectorDrawable = ResourcesCompat.getDrawable(context.resources, id, null)

        //Verifica se o Vetor não é nulo e inclui o marcador padrão
        if(vectorDrawable==null){
            return BitmapDescriptorFactory.defaultMarker()
        }
        //Cria um tipo de bitmap
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        //Cria o canvas com o bitmap
        val canvas = Canvas(bitmap)

        //Cria os limites
        vectorDrawable.setBounds(0,0,canvas.width,canvas.height)
        //Inclui a cor
        DrawableCompat.setTint(vectorDrawable, color)
        //Inclui o icone
        vectorDrawable.draw(canvas)

        //Retorna o icone completo
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}