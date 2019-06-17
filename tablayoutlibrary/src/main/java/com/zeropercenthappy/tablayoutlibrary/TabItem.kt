package com.zeropercenthappy.tablayoutlibrary

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zeropercenthappy.utilslibrary.utils.ConvertUtils

class TabItem(context: Context,
              private val unSelectedIconRes: Int? = null,
              private val selectedIconRes: Int? = null,
              val tabText: String? = null,
              private val unSelectedTextColorRes: Int? = null,
              private val selectedTextColorRes: Int? = null,
              private val innerPadding: Float = 0f,
              private val iconSize: Float = ConvertUtils.dp2px(context, 30f).toFloat(),
              private val textSize: Float = ConvertUtils.sp2px(context, 14f).toFloat(),
              private val spacingOfIconAndText: Float = 0f,
              private val scaleType: Int = 0) : LinearLayout(context) {
    private var imageView: ImageView? = null
    private var textView: TextView? = null

    init {
        val layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 1f
        this.layoutParams = layoutParams
        setPadding(0, Math.round(innerPadding), 0, Math.round(innerPadding))
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL

        if (unSelectedIconRes != null && selectedIconRes != null) {
            initTabIcon()
        }
        if (!TextUtils.isEmpty(tabText)) {
            initTabText()
        }
    }

    private fun initTabIcon() {
        imageView = ImageView(context)
        val layoutParams = LayoutParams(Math.round(iconSize), Math.round(iconSize))
        imageView!!.layoutParams = layoutParams
        imageView!!.setImageResource(unSelectedIconRes!!)
        ZPHTabLayoutConfig.setScaleType(imageView!!, this@TabItem.scaleType)
        addView(imageView)
    }

    private fun initTabText() {
        textView = TextView(context)
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT)
        if (imageView != null) {
            layoutParams.setMargins(0, Math.round(spacingOfIconAndText), 0, 0)
        }
        textView!!.layoutParams = layoutParams
        textView!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView!!.text = tabText
        addView(textView)
    }

    /**
     * 切换选中状态
     *
     * @param selected true:选中  false：未选中
     */
    fun setTabSelected(selected: Boolean) {
        if (selected) {
            imageView?.run {
                setImageResource(selectedIconRes!!)
            }
            textView?.run {
                setTextColor(ContextCompat.getColor(context, selectedTextColorRes!!))
            }
        } else {
            imageView?.run {
                setImageResource(unSelectedIconRes!!)
            }
            textView?.run {
                setTextColor(ContextCompat.getColor(context, unSelectedTextColorRes!!))
            }
        }
    }
}