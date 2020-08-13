/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/2/20 10:38 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Vertical

import android.content.Context
import android.graphics.Canvas
import android.text.TextPaint
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class VerticalMaterialButton(context: Context, attrs: AttributeSet?) : MaterialButton(context, attrs) {

    var topDown = true

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)

        setMeasuredDimension(measuredHeight, measuredWidth)

    }

    override fun onDraw(canvas: Canvas) {

        val textPaint: TextPaint = paint
        textPaint.color = currentTextColor
        textPaint.drawableState = drawableState

        canvas.save()

        if (topDown) {
            canvas.translate(width.toFloat(), 0F)
            canvas.rotate(90F)
        } else {
            canvas.translate(0F, height.toFloat())
            canvas.rotate((-90F))
        }

        canvas.translate(compoundPaddingLeft.toFloat(), extendedPaddingTop.toFloat())

        layout.draw(canvas)

        canvas.restore()

    }

}