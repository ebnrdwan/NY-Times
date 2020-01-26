package com.ebnrdwan.task.data.models

import com.ebnrdwan.task.R

data class DrawerModel(var icon: Int, var name: Int) {

    companion object {
        fun getDrawerMenu(): List<DrawerModel> {
            return listOf(
                DrawerModel(
                    R.drawable.ic_menu_news,
                    R.string.menu_news
                ),
                DrawerModel(
                    R.drawable.ic_menu_map,
                    R.string.menu_map
                ),
                DrawerModel(
                    R.drawable.ic_menu_events,
                    R.string.menu_events
                ),
                DrawerModel(
                    R.drawable.ic_menu_leadership,
                    R.string.menu_leadership
                ),
                DrawerModel(
                    R.drawable.ic_menu_language,
                    R.string.menu_language
                )
            )
        }
    }
}
