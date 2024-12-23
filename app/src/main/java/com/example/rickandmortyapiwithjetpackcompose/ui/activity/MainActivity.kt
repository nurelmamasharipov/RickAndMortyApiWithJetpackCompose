package com.example.rickandmortyapiwithjetpackcompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rickandmortyapiwithjetpackcompose.ui.navigation.MainScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.theme.RickAndMortyApiWithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyApiWithJetpackComposeTheme {
                MainScreen()
            }
        }
    }
}
