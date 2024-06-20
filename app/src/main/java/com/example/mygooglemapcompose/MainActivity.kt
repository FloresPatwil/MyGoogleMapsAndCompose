package com.example.mygooglemapcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.mygooglemapcompose.ui.theme.MyGoogleMapComposeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyGoogleMapComposeTheme {
                MyGoogleMaps()
            }
        }
    }
}

@Composable
fun MyGoogleMaps() {
    val centroCulturalunsa = LatLng(-16.398074, -71.537280)
    val salaCulturalMonasterioSC = LatLng(-16.396122, -71.536472)

    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-16.397018, -71.537072), 17f)
    }

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = centroCulturalunsa),
            title = "Centro cultural UNSA",
            snippet = "Santa Catalina 101, Arequipa 04001",
            onInfoWindowClick = {
                dialogTitle = "Centro cultural UNSA"
                dialogMessage = "Santa Catalina 101, Arequipa 04001"
                showDialog = true
            }
        )
        Marker(
            state = rememberMarkerState(position = salaCulturalMonasterioSC),
            title = "Sala ext Monasterio Santa Catalina",
            snippet = "Santa Catalina 301, Arequipa 04001",
            onInfoWindowClick = {
                dialogTitle = "Sala ext Monasterio Santa Catalina"
                dialogMessage = "Santa Catalina 301, Arequipa 04001"
                showDialog = true
            }
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = dialogTitle) },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}
//
//@Composable
//fun MyGoogleMaps(){
//    val centroCulturalunsa = LatLng(-16.398074, -71.537280)
//    val salaCulturalMonasterioSC = LatLng(-16.396122, -71.536472)
//
//    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
//    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        properties = properties,
//        uiSettings = uiSettings,
//    ){
//        Marker(MarkerState(position = centroCulturalunsa), title = "Centro cultural UNSA", snippet = "Santa Catalina 101, Arequipa 04001", )
//
//        Marker(MarkerState(position = salaCulturalMonasterioSC), title = "Sala ext Monasterio Santa Catalina", snippet = "Santa Catalina 301, Arequipa 04001")
//    }
//    //GoogleMap(modifier = Modifier.fillMaxSize())
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyGoogleMapComposeTheme {
        Greeting("Android")
    }
}