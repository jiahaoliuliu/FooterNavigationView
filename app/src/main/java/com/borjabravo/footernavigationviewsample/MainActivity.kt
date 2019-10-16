package com.borjabravo.footernavigationviewsample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.borjabravo.footernavigationview.FooterNavigationView
import com.borjabravo.footernavigationviewsample.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var activityMainBinding: ActivityMainBinding? = null
    private var optionSelected = R.id.menu_add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set up toolbar
        activityMainBinding?.toolbar?.setNavigationIcon(R.drawable.ic_menu)
        activityMainBinding?.toolbar?.setNavigationOnClickListener { activityMainBinding?.drawerLayout?.openDrawer(GravityCompat.START) }

        // Set up footer navigation view
        activityMainBinding?.footerNavigationView?.setCheckedItem(optionSelected)
        activityMainBinding?.footerNavigationView?.navigationView?.menu?.performIdentifierAction(optionSelected, 0)
        activityMainBinding?.footerNavigationView?.setNavigationListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        activityMainBinding?.drawerLayout?.closeDrawers()
        optionSelected = menuItem.itemId
        return true
    }
}