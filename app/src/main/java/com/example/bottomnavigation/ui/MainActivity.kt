package com.example.bottomnavigation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bottomnavigation.R
import com.example.bottomnavigation.databinding.ActivityMainBinding
import com.example.bottomnavigation.extension.active
import com.example.bottomnavigation.extension.switchFragment
import com.example.bottomnavigation.helper.BottomNavigationPosition
import com.example.bottomnavigation.helper.createFragment
import com.example.bottomnavigation.helper.findNavigationPositionById
import com.example.bottomnavigation.helper.getTag
import com.gyf.immersionbar.ImmersionBar
import tech.gujin.toast.ToastUtil
import java.util.Timer
import java.util.TimerTask


class MainActivity : AppCompatActivity() {

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    private lateinit var binding: ActivityMainBinding
    private var mBackKeyPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
        restoreSavedInstanceState(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.itemIconTintList = null
        binding.ivHome.setOnClickListener {
            binding.bottomNavigation.menu.getItem(2).isChecked = true;
            binding.ivHome.setImageResource(R.mipmap.icon_navi_main_sel)
            navPosition = BottomNavigationPosition.HOME
            binding.bottomNavigation.active(navPosition.position)
            switchFragment(navPosition)
        }
        binding.bottomNavigation.selectedItemId = R.id.navigation_home
        binding.ivHome.setImageResource(R.mipmap.icon_navi_main_sel)

        binding.bottomNavigation.apply {
            // Set a default position
             // Extension function
            active(navPosition.position)
            // Set a listener for handling selection events on bottom navigation items
            setOnItemSelectedListener { item ->
                navPosition = findNavigationPositionById(item.itemId)
                if (item.itemId == R.id.navigation_home) {
                    binding.ivHome.setImageResource(R.mipmap.icon_navi_main_sel)
//                    binding.bottomNavigation.menu.getItem(2).isChecked = true;
                } else {
                    binding.ivHome.setImageResource(R.mipmap.icon_navi_main_unsel)
                }

                switchFragment(navPosition)
            }
        }

        // Add the home fragment
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)
    }

    override fun onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtil.show("再按一次退出程序")
            mBackKeyPressed = true
            Timer().schedule(object : TimerTask() {
                //延时两秒，如果超出则擦错第一次按键记录
                override fun run() {
                    mBackKeyPressed = false
                }
            }, 2000)
        } else { //退出程序
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Store the current navigation position.
        outState.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)?.also {
            navPosition = findNavigationPositionById(it)
        }
    }

    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return findFragment(navPosition).let {
            supportFragmentManager.switchFragment(it, navPosition.getTag()) // Extension function
        }
    }

    private fun findFragment(position: BottomNavigationPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
            ?: position.createFragment()
    }

    companion object {
        const val KEY_POSITION = "keyPosition"
    }
}
