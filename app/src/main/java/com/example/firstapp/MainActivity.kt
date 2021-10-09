package com.example.firstapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstapp.fragments.AlarmFragment
import com.example.firstapp.fragments.StopperFragment
import com.example.firstapp.fragments.TimerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = this.findViewById(R.id.bottomNavigation)
        this.supportFragmentManager.beginTransaction().replace(R.id.main_container, AlarmFragment()).commit()
        bottomNavigationView.selectedItemId = R.id.nav_alarm
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val currentItemId = bottomNavigationView.selectedItemId
        if(currentItemId == item.itemId)
            return true

        lateinit var desiredFragment: Fragment
        when(item.itemId) {
            R.id.nav_alarm -> desiredFragment = AlarmFragment()
            R.id.nav_stopper -> desiredFragment = StopperFragment()
            R.id.nav_timer -> desiredFragment = TimerFragment()
        }

        if(currentItemId > item.itemId) {
            this.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.from_left, R.anim.to_right)
                .replace(R.id.main_container, desiredFragment).commit()
        } else {
            this.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.from_right, R.anim.to_left)
                .replace(R.id.main_container, desiredFragment).commit()
        }
        return true
    }
    
}