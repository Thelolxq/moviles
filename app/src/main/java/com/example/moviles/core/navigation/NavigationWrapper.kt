package com.example.moviles.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviles.HomeScreen
import com.example.moviles.view.Products.view.AddProductScreen
import com.example.moviles.view.Products.view.EditDeleteProductScreen
import com.example.moviles.view.Products.view.EditSingleProductScreen
import com.example.moviles.view.Products.view.ListProductsScreen
import com.example.moviles.view.login.view.LoginScreen

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            LoginScreen(navController = navController)
        }
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("add_product_screen") {
            AddProductScreen(navController = navController)
        }
        composable("list_products_screen") {
            ListProductsScreen(navController)
        }
        composable("edit_delete_product_screen") {
            EditDeleteProductScreen(navController)
        }
        composable(
            route = "edit_single_product_screen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            EditSingleProductScreen(navController, backStackEntry.arguments?.getString("productId"))
        }

    }
}