/*
 * Copyright (C) 2017 Borja Bravo Álvarez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.borjabravo.footernavigationview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewStub
import androidx.annotation.IdRes
import androidx.core.widget.NestedScrollView
import com.google.android.material.navigation.NavigationView

class FooterNavigationView : NestedScrollView {

    var navigationView: NavigationView? = null
        private set
    var menu: Int = 0
    var footerView: View? = null
    var headerLayout: Int = 0
    var footerLayout: Int = 0
    var itemIconTint: ColorStateList? = null
        set(value) {
            navigationView?.itemIconTintList = value
            field = value
        }
    var itemTextColor: ColorStateList? = null
        set(value) {
            navigationView?.itemTextColor = value
            field = value
        }
    var itemBackground: Drawable? = null
        set(value) {
            navigationView?.itemBackground = value
            field = value
        }

    constructor(context: Context) : super(context) {
            init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
            init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(getContext(), R.layout.footer_navigation_view, this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FooterNavigationView)
        this.navigationView = findViewById(R.id.nav_view) as NavigationView
        this.headerLayout = typedArray.getResourceId(R.styleable.FooterNavigationView_headerLayout, 0)
        this.menu = typedArray.getResourceId(R.styleable.FooterNavigationView_menu, 0)
        this.itemIconTint = typedArray.getColorStateList(R.styleable.FooterNavigationView_itemIconTint)
        this.itemTextColor = typedArray.getColorStateList(R.styleable.FooterNavigationView_itemTextColor)
        this.itemBackground = typedArray.getDrawable(R.styleable.FooterNavigationView_itemBackground)
        this.footerLayout = typedArray.getResourceId(R.styleable.FooterNavigationView_footerLayout, 0)
        var footerViewStub = findViewById<ViewStub>(R.id.footer)

        navigationView?.inflateHeaderView(headerLayout)
        navigationView?.inflateMenu(menu)
        footerViewStub.layoutResource = footerLayout
        footerView = footerViewStub.inflate()
        typedArray.recycle()
    }

    fun setNavigationListener(listener: NavigationView.OnNavigationItemSelectedListener) {
        navigationView?.setNavigationItemSelectedListener(listener)
    }

    fun setCheckedItem(@IdRes id: Int) {
        navigationView?.setCheckedItem(id)
    }
}