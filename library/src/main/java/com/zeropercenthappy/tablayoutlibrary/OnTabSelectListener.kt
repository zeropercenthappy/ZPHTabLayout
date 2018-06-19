package com.zeropercenthappy.tablayoutlibrary

interface OnTabSelectListener {
    /**
     * @param position  点击的tab的位置
     * @param tabItem   点击的tab实例
     * @return 是否需要改变tab的选中状态
     */
    fun onTabSelect(position: Int, tabItem: TabItem): Boolean
}