package com.clicknext.pattern.utils.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.clicknext.pattern.R

class CustomFontTextView : TextView {

    private var customFont: String? = null

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyCustomFont(context , attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyCustomFont(context , attrs)
    }

    private fun applyCustomFont(context: Context, attrs: AttributeSet) {

        val style = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomFontTextView
        )

        val fontName = when (style.getInteger(R.styleable.CustomFontTextView_fontName, 0)) {
            1 -> R.string.Kanit_Black
            2 -> R.string.Kanit_BlackItalic
            3 -> R.string.Kanit_Bold
            4 -> R.string.Kanit_BoldItalic
            5 -> R.string.Kanit_ExtraBold
            6 -> R.string.Kanit_ExtraBoldItalic
            7 -> R.string.Kanit_ExtraLight
            8 -> R.string.Kanit_ExtraLightItalic
            9 -> R.string.Kanit_Italic
            10 -> R.string.Kanit_Light
            11 -> R.string.Kanit_LightItalic
            12 -> R.string.Kanit_Medium
            13 -> R.string.Kanit_MediumItalic
            14 -> R.string.Kanit_Regular
            15 -> R.string.Kanit_SemiBold
            16 -> R.string.Kanit_SemiBoldItalic
            17 -> R.string.Kanit_Thin
            18 -> R.string.Kanit_ThinItalic
            19 -> R.string.Sarabun_Bold
            20 -> R.string.Sarabun_BoldItalic
            21 -> R.string.Sarabun_ExtraBold
            22 -> R.string.Sarabun_ExtraBoldItalic
            23 -> R.string.Sarabun_ExtraLight
            24 -> R.string.Sarabun_ExtraLightItalic
            25 -> R.string.Sarabun_Italic
            26 -> R.string.Sarabun_Light
            27 -> R.string.Sarabun_LightItalic
            28 -> R.string.Sarabun_Medium
            29 -> R.string.Sarabun_MediumItalic
            30 -> R.string.Sarabun_Regular
            31 -> R.string.Sarabun_SemiBold
            32 -> R.string.Sarabun_SemiBoldItalic
            33 -> R.string.Sarabun_Thin
            34 -> R.string.Sarabun_ThinItalic
            35 -> R.string.Ionicons
            else -> R.string.Sarabun_Regular
        }

        customFont = resources.getString(fontName)

        val tf = Typeface.createFromAsset(
            context.assets,
            "fonts/$customFont.ttf"
        )
        typeface = tf
        style.recycle()
    }

}