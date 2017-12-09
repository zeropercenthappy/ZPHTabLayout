package com.zeropercenthappy.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeropercenthappy.utilslibrary.ConvertUtils;

/**
 * @author ybq
 * @date 2017/7/13
 */

public class ZPHTabLayout extends LinearLayout implements View.OnClickListener {
    private float textSize;
    private float iconSize;
    private float innerPadding;
    private float spacingOfIconAndText;
    private int scaleType;
    private OnTabSelected onTabSelected;

    public ZPHTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZPHTabLayout);
        textSize = typedArray.getDimensionPixelSize(R.styleable.ZPHTabLayout_textSize,
                ConvertUtils.sp2px(getContext(), 14));
        iconSize = typedArray.getDimensionPixelSize(R.styleable.ZPHTabLayout_iconSize,
                ConvertUtils.dp2px(getContext(), 30));
        innerPadding = typedArray.getDimension(R.styleable.ZPHTabLayout_innerPadding, 0);
        spacingOfIconAndText =
                typedArray.getDimension(R.styleable.ZPHTabLayout_spacingOfIconAndText, 0);
        scaleType = typedArray.getInt(R.styleable.ZPHTabLayout_scaleType, 0);
        typedArray.recycle();
    }

    public OnTabSelected getOnTabSelected() {
        return onTabSelected;
    }

    public void setOnTabSelected(OnTabSelected onTabSelected) {
        this.onTabSelected = onTabSelected;
    }

    public void addTabItem(TabItem tabItem) {
        addView(tabItem);
        tabItem.setOnClickListener(this);
    }

    public void performTabClick(int position) {
        if (position < getChildCount()) {
            getChildAt(position).performClick();
        }
    }

    public void changeTabSelected(int position) {
        int childCount = getChildCount();
        if (position > childCount - 1) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            TabItem tabItem = ((TabItem) getChildAt(i));
            tabItem.setTabSelected(false);
        }
        ((TabItem) getChildAt(position)).setTabSelected(true);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TabItem && onTabSelected != null) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                boolean changeStatue = (v == getChildAt(i) &&
                        onTabSelected.onTabSelected(i, (TabItem) getChildAt(i)));
                if (changeStatue) {
                    changeTabSelected(i);
                }
            }
        }
    }

    public TabItem buildTabItem(int selectedIconRes,
                                int unSelectedIconRes,
                                String tabText,
                                @ColorRes int selectedTextColor,
                                @ColorRes int unSelectedTextColor) {
        return new TabItem(getContext(),
                selectedIconRes,
                unSelectedIconRes,
                tabText,
                selectedTextColor,
                unSelectedTextColor);
    }

    /**
     * 点击Tab时的回调监听
     */
    public interface OnTabSelected {
        /**
         * @param position 点击的Tab的位置
         * @return true：改变Tab的状态 <br> false：不需要改变Tab的状态
         */
        boolean onTabSelected(int position, TabItem tabItem);
    }

    public class TabItem extends LinearLayout {
        private ImageView imageView;
        private TextView textView;

        private int selectedIconRes = -1;
        private int unSelectedIconRes = -1;

        private String tabText = null;
        private int selectedTextColor = -1;
        private int unSelectedTextColor = -1;

        private TabItem(Context context,
                        int selectedIconRes,
                        int unSelectedIconRes,
                        String tabText,
                        int selectedTextColor,
                        int unSelectedTextColor) {
            super(context);
            this.selectedIconRes = selectedIconRes;
            this.unSelectedIconRes = unSelectedIconRes;
            this.tabText = tabText;
            this.selectedTextColor = selectedTextColor;
            this.unSelectedTextColor = unSelectedTextColor;
            init();
        }

        public String getTabText() {
            return tabText;
        }

        private void init() {
            LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            this.setLayoutParams(layoutParams);
            setPadding(0, Math.round(innerPadding), 0, Math.round(innerPadding));
            setOrientation(VERTICAL);
            setGravity(Gravity.CENTER_HORIZONTAL);
            if (selectedIconRes != -1 && unSelectedIconRes != -1) {
                initTabIcon();
            }

            if (!TextUtils.isEmpty(tabText)) {
                initTabText();
            }
        }

        private void initTabIcon() {
            imageView = new ImageView(getContext());
            LayoutParams layoutParams =
                    new LayoutParams(Math.round(iconSize), Math.round(iconSize));
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(unSelectedIconRes);
            ZPHTabLayoutConfig.setScaleType(imageView, scaleType);
            addView(imageView);
        }

        private void initTabText() {
            textView = new TextView(getContext());
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            if (imageView != null) {
                layoutParams.setMargins(0, Math.round(spacingOfIconAndText), 0, 0);
            }
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            textView.setText(tabText);
            addView(textView);
        }

        /**
         * 切换选中状态
         *
         * @param selected true:选中  false：未选中
         */
        private void setTabSelected(boolean selected) {
            if (selected) {
                if (imageView != null) {
                    imageView.setImageResource(selectedIconRes);
                }
                if (textView != null) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), selectedTextColor));
                }
            } else {
                if (imageView != null) {
                    imageView.setImageResource(unSelectedIconRes);
                }
                if (textView != null) {
                    textView.setTextColor(ContextCompat.getColor(getContext(),
                            unSelectedTextColor));
                }
            }
        }

    }
}
