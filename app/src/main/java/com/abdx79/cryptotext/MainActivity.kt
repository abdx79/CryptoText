package com.abdx79.cryptotext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdx79.cryptotext.ui.MakeHistoryScreen
import com.abdx79.cryptotext.ui.MakeHomeScreen
import com.abdx79.cryptotext.ui.theme.CryptoTextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTextTheme {
                MakeContentScreen()
            }
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeContentScreen() {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf(ScreenRoutes.Home.route) }
    
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("CryptoText") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }, content = { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(navController, ScreenRoutes.Home.route, modifier) {
            composable(ScreenRoutes.Home.route) { MakeHomeScreen() }
            composable(ScreenRoutes.History.route) { MakeHistoryScreen() }
        }

    }, bottomBar = {
        NavigationBar {
            ScreenRoutes.getRoutes().forEach {
                NavigationBarItem(selected = selectedScreen == it.route,
                    onClick = { navController.navigate(it.route); selectedScreen = it.route },
                    label = { Text(it.route) },
                    alwaysShowLabel = false,
                    icon = { Icon(painterResource(id = it.icon), contentDescription = it.route) })
            }
        }
    })
}
