package com.example.aula6kotlinmapas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

//Array com os dados dos endereços
private val places = arrayListOf(
    Place("Fiap Campus Vila Olimpia", LatLng(-23.5955843, -46.6851937),
        "Rua Olimpíadas, 186 - São Paulo - SP", 4.8f),
    Place("Fiap Campus Paulista", LatLng(-23.5643721, -46.652857),
        "Av. Paulista, 1106 - São Paulo - SP", 5.0f),
    Place("Fiap Campus Vila Mariana", LatLng(-23.5746685, -46.6232043),
        "Av. Lins de Vasconcelos, 1264 - São Paulo - SP", 4.8f)
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        mapFragment.getMapAsync{

            //Adicionar os marcadores
            googleMap -> addMarkers(googleMap)

            googleMap.setOnMapLoadedCallback {

                //Criando os limites
                val bounds = LatLngBounds.builder()

                //Incluir os endereços no limite
                places.forEach {
                    bounds.include(it.latLng)
                }
                //Ajustando a camera para aparecer com os pontos dentro do limite
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
        }
    }

    //Incluindo o marcador para cada endereço do array
    private fun addMarkers(googleMap: GoogleMap){
        places.forEach { place ->
            val market = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
                    .icon(BitmapHelper.vectorToBitmap(
                        this, R.drawable.ic_baseline_school_24,
                        ContextCompat.getColor(this, R.color.purple_700)))
            )

        }
    }
}

// Criando a classe dos endereços
data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
)