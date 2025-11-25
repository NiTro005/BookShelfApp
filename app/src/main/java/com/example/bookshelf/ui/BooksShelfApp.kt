package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.ui.screens.BookHomeScreen
import com.example.bookshelf.ui.screens.LoadingScreen

enum class Example {
    LOAD, HOME, DETAIL, HOME_AND_DETAIL
}

enum class NavType {
    LIST, LIST_AND_DETAIL
}

@Composable
fun BooksShelfApp(winSize: WindowWidthSizeClass) {
    val navController: NavHostController = rememberNavController()
    val viewModel: BooksMainViewModel = viewModel(factory = BooksMainViewModel.Companion.Factory)
    val uiState: BooksUiState by viewModel.book.collectAsState()
    val navType = when(winSize) {
        WindowWidthSizeClass.Compact -> NavType.LIST
        WindowWidthSizeClass.Expanded -> NavType.LIST_AND_DETAIL
        else -> NavType.LIST
    }
    NavHost(
        navController = navController,
        startDestination = Example.LOAD.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Example.LOAD.name) {
            LoadingScreen(
                loadComplete = {
                    when (navType) {
                        NavType.LIST -> navController.navigate(Example.HOME.name)
                        NavType.LIST_AND_DETAIL -> navController.navigate(Example.HOME_AND_DETAIL.name)
                    }
                }
            )
        }

        composable(Example.HOME.name){ bookId ->
            BookHomeScreen(
                uiState = uiState,
                onBookClick = {
                    navController.navigate("${Example.DETAIL.name}/${bookId}")
                    viewModel.bookDetails(bookId.toString())
                }
            )
        }

        composable("${Example.DETAIL.name}/{bookId}") { backStackEntree ->
            TODO()
        }

        composable(Example.HOME_AND_DETAIL.name) {
            ExtractSizeApp()
        }
    }
}
