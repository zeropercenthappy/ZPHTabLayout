package com.zeropercenthappy.tablayoutlibrary

import android.content.Context
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.sp

class ZPHTabLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), View.OnClickListener {
    private val textSize: Float
    private val iconSize: Float
    private val innerPadding: Float
    private val spacingOfIconAndText: Float
    private val scaleType: Int
    var onTabSelectListener: OnTabSelectListener? = null

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZPHTabLayout)
        textSize = typedArray.getDimensionPixelSize(R.styleable.ZPHTabLayout_textSize, context.sp(14)).toFloat()
        iconSize = typedArray.getDimensionPixelSize(R.styleable.ZPHTabLayout_iconSize, context.dip(30)).toFloat()
        innerPadding = typedArray.getDimension(R.styleable.ZPHTabLayout_innerPadding, 0f)
        spacingOfIconAndText = typedArray.getDimension(R.styleable.ZPHTabLayout_spacingOfIconAndText, 0f)
        scaleType = typedArray.getInt(R.styleable.ZPHTabLayout_scaleType, 0)
        typedArray.recycle()
    }

    fun addTabItem(tabItem: TabItem) {
        addView(tabItem)
        tabItem.setOnClickListener(this)
    }

    fun performTabClick(position: Int) {
        if (position < childCount) {
            getChildAt(position).performClick()
        }
    }

    fun changeTabSelected(position: Int) {
        val childCount = childCount
        if (position > childCount - 1) {
            return
        }
        for (i in 0 until childCount) {
            val tabItem = getChildAt(i) as TabItem
            tabItem.setTabSelected(false)
        }
        (getChildAt(position) as TabItem).setTabSelected(true)
    }

    fun buildTabItem(unSelectedIconRes: Int,
                     selectedIconRes: Int,
                     tabText: String,
                     @ColorRes unSelectedTextColor: Int,
                     @ColorRes selectedTextColor: Int): TabItem {
        return TabItem(context,
                unSelectedIconRes = unSelectedIconRes,
                selectedIconRes = selectedIconRes,
                tabText = tabText,
                unSelectedTextColorRes = unSelectedTextColor,
                selectedTextColorRes = selectedTextColor,
                innerPadding = innerPadding,
                iconSize = iconSize,
                textSize = textSize,
                spacingOfIconAndText = spacingOfIconAndText,
                scaleType = scaleType)
    }

    override fun onClick(v: View?) {
        if (v is TabItem && onTabSelectListener != null) {
            for (i in 0 until childCount) {
                val changeStatue = v === getChildAt(i) && onTabSelectListener!!.onTabSelect(i, getChildAt(i) as TabItem)
                if (changeStatue) {
                    changeTabSelected(i)
                }
            }
        }
    }
}