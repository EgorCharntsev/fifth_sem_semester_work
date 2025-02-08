package ru.kpfu.itis.fifthsemwork.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.kpfu.itis.designsystem.FifthSemWorkTheme
import ru.kpfu.itis.designsystem.components.navigation.BottomNavigationBar
import ru.kpfu.itis.fifthsemwork.navigation.NavHostContainer
import ru.kpfu.itis.fifthsemwork.navigation.Navigator
import javax.inject.Inject
import ru.kpfu.itis.fifthsemwork.navigation.bottomNavigationItems

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            var bottomBarVisible by remember { mutableStateOf(true)}

            Surface(color = MaterialTheme.colorScheme.surface) {
                Scaffold (
                    topBar = {

                    },
                    bottomBar = {
                        if (bottomBarVisible) {
                            BottomNavigationBar(
                                navController = navController,
                                items = bottomNavigationItems
                            )
                        }
                    },
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            NavHostContainer(
                                navController = navController,
                                navigator = navigator,
                                startDestination = viewModel.getStartDestination(),
                            ) { isVisible ->
                                bottomBarVisible = isVisible
                            }
                        }
                    }
                )
            }
//            FifthSemWorkTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }


//}
//
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContent {
//            PlannerTheme {
//                val startDestination = if (Firebase.auth.currentUser != null) HomeRoute else LoginRoute
//                Navigation(startDestination)
//            }
//        }
//    }
}

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
    FifthSemWorkTheme {
        Greeting("Android")
    }
}