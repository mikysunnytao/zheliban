package com.example.bottomnavigation.helper

import androidx.fragment.app.Fragment
import com.example.bottomnavigation.R
import com.example.bottomnavigation.ui.CityFragment
import com.example.bottomnavigation.ui.DashboardFragment
import com.example.bottomnavigation.ui.HomeFragment
import com.example.bottomnavigation.ui.NotificationsFragment
import com.example.bottomnavigation.ui.ProfileFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    CITY(0, R.id.home),
    DASHBOARD(1, R.id.dashboard),
    HOME(2,R.id.navigation_home),
    NOTIFICATIONS(3, R.id.notifications),
    PROFILE(4, R.id.profile);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.CITY.id -> BottomNavigationPosition.CITY
    BottomNavigationPosition.DASHBOARD.id -> BottomNavigationPosition.DASHBOARD
    BottomNavigationPosition.HOME.id ->BottomNavigationPosition.HOME
    BottomNavigationPosition.NOTIFICATIONS.id -> BottomNavigationPosition.NOTIFICATIONS
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.CITY
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.CITY -> CityFragment.newInstance()
    BottomNavigationPosition.DASHBOARD -> DashboardFragment.newInstance()
    BottomNavigationPosition.HOME -> HomeFragment.newInstance()
    BottomNavigationPosition.NOTIFICATIONS -> NotificationsFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.CITY -> CityFragment.TAG
    BottomNavigationPosition.DASHBOARD -> DashboardFragment.TAG
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.NOTIFICATIONS -> NotificationsFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}

