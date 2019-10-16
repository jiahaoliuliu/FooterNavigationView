package com.borjabravo.footernavigationviewsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.borjabravo.footernavigationview.FooterNavigationView
import com.borjabravo.footernavigationviewsample.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var activityMainBinding: ActivityMainBinding? = null
    private var footerTitle: TextView? = null
    private var footerSubtitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set up toolbar
        activityMainBinding?.toolbar?.setNavigationIcon(R.drawable.ic_menu)
        activityMainBinding?.toolbar?.setNavigationOnClickListener { activityMainBinding?.drawerLayout?.openDrawer(GravityCompat.START) }

        // Set up the footer
        footerTitle = activityMainBinding?.footerNavigationView?.footerView?.findViewById(R.id.footer_title)
        footerSubtitle = activityMainBinding?.footerNavigationView?.footerView?.findViewById(R.id.footer_subtitle)
        footerTitle?.text = getText(R.string.name)
        footerSubtitle?.text = getText(R.string.copyright)

        // Set up footer navigation view
        activityMainBinding?.footerNavigationView?.setNavigationListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        activityMainBinding?.drawerLayout?.closeDrawers()
        updateViews(menuItem)
        return true
    }

    private fun updateViews(menuItem: MenuItem) {
        activityMainBinding?.footerNavigationView?.setCheckedItem(menuItem.itemId)
        activityMainBinding?.toolbar?.title = menuItem.title
        activityMainBinding?.mainText?.text = menuItem.title
    }
}