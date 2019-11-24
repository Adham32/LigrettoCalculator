package com.pl.adambartosik.ligrettocalculator.view.activites

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.view.fragments.menu.MenuFragmentComingSoon
import com.pl.adambartosik.ligrettocalculator.view.fragments.menu.MenuFragmentGame
import com.pl.adambartosik.ligrettocalculator.view.fragments.menu.MenuFragmentPlayer
import com.pl.adambartosik.ligrettocalculator.view.fragments.menu.MenuFragmentSettings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentsList: ArrayList<Fragment>
    private lateinit var activeFragment: Fragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_games -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentsList[0]).commit()
                activeFragment = fragmentsList[0]
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_players -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentsList[1]).commit()
                activeFragment = fragmentsList[1]
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentsList[2]).commit()
                activeFragment = fragmentsList[2]
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        createFragments()
    }

    // active first fragment
    private fun activeFirstFragment(){
        activeFragment = fragmentsList[0]
        supportFragmentManager.beginTransaction().show(activeFragment).commit()
    }

    // create fragments to activity
    private fun createFragments() {
        fragmentsList = ArrayList()
        addToFragmentList(MenuFragmentGame.newInstance())
        addToFragmentList(MenuFragmentPlayer.newInstance())
        addToFragmentList(MenuFragmentComingSoon.newInstance())
        activeFirstFragment()
    }

    // operation add new fragment to list
    private fun addToFragmentList(frag : Fragment){
        fragmentsList.add(frag)
        supportFragmentManager.beginTransaction().add(container_fragments_fl_am.id, frag, frag.tag).hide(frag).commit()
    }
}
