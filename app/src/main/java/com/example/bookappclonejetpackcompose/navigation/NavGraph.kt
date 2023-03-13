package com.example.bookappclonejetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookappclonejetpackcompose.view.BookDetailsScreen
import com.example.bookappclonejetpackcompose.view.BookListScreen
import com.example.bookappclonejetpackcompose.viewmodel.MainViewModel
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
object EndPoints {
    const val ID = "id"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val actions = remember(navController) { MainActions(navController) }


    NavHost(
        navController = navController,
        startDestination = com.example.bookappclonejetpackcompose.navigation.Screen.BookList.route
    ) {
        composable(com.example.bookappclonejetpackcompose.navigation.Screen.BookList.route) {
            val viewModel = hiltViewModel<MainViewModel>(it)
            viewModel.getAllBooks(context)
            BookListScreen(viewModel = viewModel, actions = actions)
        }
        composable("${com.example.bookappclonejetpackcompose.navigation.Screen.Details.route}/{id}",
            arguments = listOf(navArgument(EndPoints.ID) { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")
                ?: throw IllegalArgumentException("Missing ID parameter")
            val viewModel = hiltViewModel<MainViewModel>(it)
            viewModel.getBookById(context, id)
            BookDetailsScreen(viewModel = viewModel, actions = actions)
        }

    }

}

class MainActions(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoBookDetails: (String) -> Unit = { isbn ->
        navController.navigate("${com.example.bookappclonejetpackcompose.navigation.Screen.Details.route}/$isbn")
    }

    val gotoBookList: () -> Unit = {
        navController.navigate(com.example.bookappclonejetpackcompose.navigation.Screen.BookList.route)
    }


}