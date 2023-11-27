# ComposeTemplate
Starter template for Compose with Hilt, Coil, and Retrofit

## How to Use
### Clone
`git clone https://github.com/baec23/ComposeTemplate.git --branch main`
### Run customizer.sh
`bash customizer.sh {newPackageName} {newAppName}`

## Useful LiveTemplates for Android Studio
### compnav - Compose Navigation Boilerplate (No Arguments)
```
/*
*
* TODO: Add route to NavHost
*
*/
const val $SCREEN_NAME_ROUTE_CC$ = "$SCREEN_NAME_ROUTE_SC$"
fun androidx.navigation.NavGraphBuilder.$SCREEN_NAME_CC$(){
    composable(route = $SCREEN_NAME_ROUTE_CC$){
        $NAME$Screen()
    }
}
fun androidx.navigation.NavController.navigateTo$SCREEN_NAME_PC$(navOptions: androidx.navigation.NavOptions? = null){
    navigate(route = $SCREEN_NAME_ROUTE_CC$, navOptions = navOptions)
}
```


### compnavargs - Compose Navigation Boilerplate (With Arguments)
```
/*
*
* TODO: Add route to NavHost
*
*/
const val $SCREEN_NAME_ROUTE_CC$ = "$SCREEN_NAME_ROUTE_SC$"
fun androidx.navigation.NavGraphBuilder.$SCREEN_NAME_CC$() {
    composable(
        route = "$$$SCREEN_NAME_ROUTE_CC$/{$ARG_NAME$}",
        arguments = listOf(navArgument("$ARG_NAME$") {
            type = androidx.navigation.NavType.IntType
            nullable = false
        })
    ) {
        val $ARG_NAME$ = it.arguments?.getInt("$ARG_NAME$")
        $ARG_NAME$?.let{
            val viewModel: $NAME$ViewModel = hiltViewModel()
            //TODO: Set Arg in ViewModel
            $SCREEN_NAME_PC$(viewModel = viewModel, $ARG_NAME$ = $ARG_NAME$)
        }
    }
}
fun androidx.navigation.NavController.navigateTo$SCREEN_NAME_PC$($ARG_NAME$: Int, navOptions: androidx.navigation.NavOptions? = null){
    val routeWithArguments = "$$$SCREEN_NAME_ROUTE_CC$/$$$ARG_NAME$"
    navigate(route = routeWithArguments, navOptions = navOptions)
}
```


### compscreen - Compose Screen with ViewModel
```
/*
*
* TODO: Add route to NavHost
*
*/
const val $SCREEN_NAME_ROUTE_CC$ = "$SCREEN_NAME_ROUTE_SC$"
fun androidx.navigation.NavGraphBuilder.$SCREEN_NAME_CC$(){
    composable(route = $SCREEN_NAME_ROUTE_CC$){
        $NAME$Screen()
    }
}
fun androidx.navigation.NavController.navigateTo$SCREEN_NAME_PC$(navOptions: androidx.navigation.NavOptions? = null){
    navigate(route = $SCREEN_NAME_ROUTE_CC$, navOptions = navOptions)
}
@androidx.compose.runtime.Composable
fun $NAME$Screen(
    viewModel: $NAME$ViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
$END$
}
```


### compscreenargs - Compose Screen with ViewModel and Navigation Arguments
```
/*
*
* TODO: Add route to NavHost
*
*/
const val $SCREEN_NAME_ROUTE_CC$ = "$SCREEN_NAME_ROUTE_SC$"
fun androidx.navigation.NavGraphBuilder.$SCREEN_NAME_CC$() {
    composable(
        route = "$$$SCREEN_NAME_ROUTE_CC$/{$ARG_NAME$}",
        arguments = listOf(navArgument("$ARG_NAME$") {
            type = androidx.navigation.NavType.IntType
            nullable = false
        })
    ) {
        val $ARG_NAME$ = it.arguments?.getInt("$ARG_NAME$")
        $ARG_NAME$?.let{
            val viewModel: $NAME$ViewModel = hiltViewModel()
            //TODO: Set Arg in ViewModel
            $SCREEN_NAME_PC$(viewModel = viewModel, $ARG_NAME$ = $ARG_NAME$)
        }
    }
}
fun androidx.navigation.NavController.navigateTo$SCREEN_NAME_PC$($ARG_NAME$: Int, navOptions: androidx.navigation.NavOptions? = null){
    val routeWithArguments = "$$$SCREEN_NAME_ROUTE_CC$/$$$ARG_NAME$"
    navigate(route = routeWithArguments, navOptions = navOptions)
}
@androidx.compose.runtime.Composable
fun $NAME$Screen(
    viewModel: $NAME$ViewModel = androidx.hilt.navigation.compose.hiltViewModel(),
    $ARG_NAME$: Int
) {
$END$
}
```



### msf - MutableStateFlow boilerplate
```
private val _$NAME$ = kotlinx.coroutines.flow.MutableStateFlow<$TYPE$>()
val $NAME$ = _$NAME$.asStateFlow()
```



### comprand - Random placeholder content for @Composable
```
androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize().background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.material3.Text("Placeholder")
    }
```



### hvm - HiltViewModel for a Compose Screen
```
@dagger.hilt.android.lifecycle.HiltViewModel
class $NAME$ViewModel @javax.inject.Inject constructor(): androidx.lifecycle.ViewModel() {
    $END$
    fun onEvent(event: $NAME$UiEvent) {
        when(event) {
        }
    }
}

sealed class $NAME$UiEvent{
}
```


### vmcs - Compose State to be used in ViewModel for Compose Screen
```
private val _$NAME$: androidx.compose.runtime.MutableState<$TYPE$> = androidx.compose.runtime.mutableStateOf()
val $NAME$: androidx.compose.runtime.State<$TYPE$> = _$NAME$
```
