package com.zeropercenthappy.tablayoutsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zeropercenthappy.tablayoutlibrary.OnTabSelectListener
import com.zeropercenthappy.tablayoutlibrary.TabItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnTabSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        tl.onTabSelectListener = this
        tl.addTabItem(tl.buildTabItem(R.mipmap.unselect, R.mipmap.select,
                "标签1", R.color.colorPrimary, R.color.colorAccent))
        tl.addTabItem(tl.buildTabItem(R.mipmap.unselect, R.mipmap.select,
                "标签2", R.color.colorPrimary, R.color.colorAccent))
        tl.addTabItem(tl.buildTabItem(R.mipmap.unselect, R.mipmap.select,
                "标签3", R.color.colorPrimary, R.color.colorAccent))
        tl.addTabItem(tl.buildTabItem(R.mipmap.unselect, R.mipmap.select,
                "标签4", R.color.colorPrimary, R.color.colorAccent))
        tl.performTabClick(0)
    }

    override fun onTabSelect(position: Int, tabItem: TabItem): Boolean {
        tv.text = tabItem.tabText
        return true
    }

}
