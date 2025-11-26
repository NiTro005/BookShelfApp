package com.example.bookshelf.ui

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.bookshelf.ui.screens.BookDetailScreen
import com.example.bookshelf.ui.screens.BookHomeScreen
import com.example.bookshelf.ui.screens.LoadingScreen

enum class BookShelfScreenType {
    LOAD, HOME, HOME_AND_DETAIL, DETAIL
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
        startDestination = BookShelfScreenType.LOAD.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(BookShelfScreenType.LOAD.name) {
            LoadingScreen(
                uiState = uiState,
                loadComplete = {
                    when (navType) {
                        NavType.LIST -> navController.navigate(BookShelfScreenType.HOME.name)
                        NavType.LIST_AND_DETAIL -> navController.navigate(BookShelfScreenType.HOME_AND_DETAIL.name)
                    }
                },
                retryAction = viewModel::initApp,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(BookShelfScreenType.HOME.name){
            BookHomeScreen(
                uiState = uiState,
                onBookClick = { bookId ->
                    navController.navigate("${BookShelfScreenType.DETAIL.name}/${bookId}")
                    viewModel.bookDetails(bookId)
                },
                onQueryClick = { query ->
                    viewModel.searchBooks(query)
                }
            )
        }

        composable("${BookShelfScreenType.DETAIL.name}/{bookId}") {
            BookDetailScreen(
                onBackClick =  {
                    navController.popBackStack()
                },
                uiState = uiState,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(BookShelfScreenType.HOME_AND_DETAIL.name) {
            ExtractSizeApp()
        }
    }
}
