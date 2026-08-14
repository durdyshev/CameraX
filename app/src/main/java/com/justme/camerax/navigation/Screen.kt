package com.justme.camerax.navigation

sealed class Screen(val route: String) {
    data object StartupWizard : Screen("startup_wizard")
    data object ListScreen : Screen("list_screen")
    data object AddUpdateItem : Screen("add_update_item")
    data object CustomCamera : Screen("custom_camera")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}