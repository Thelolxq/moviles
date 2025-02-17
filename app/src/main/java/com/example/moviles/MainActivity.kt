package com.example.moviles

import com.example.moviles.view.Products.view.EditDeleteProductScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviles.core.navigation.NavigationWrapper
import com.example.moviles.view.Products.view.EditSingleProductScreen


import com.example.moviles.view.Products.view.ListProductsScreen
import com.example.moviles.view.Products.view.AddProductScreen
import com.example.moviles.view.login.view.LoginScreen
import com.example.moviles.view.theme.MovilesTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovilesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                NavigationWrapper()
                }
            }
        }
    }
}