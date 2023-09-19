package com.rbppl.facedetector

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.google.mlkit.vision.face.FaceContour

class FaceDetectionOverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private var faceContours: List<FaceContour>? = null

    fun setFaceContours(contours: List<FaceContour>) {
        faceContours = contours
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        faceContours?.forEach { contour ->
            val points = contour.points
            for (i in 0 until points.size - 1) {
                val startPoint = points[i]
                val endPoint = points[i + 1]
                canvas.drawLine(
                    startPoint.x, startPoint.y,
                    endPoint.x, endPoint.y, paint
                )
            }
        }
    }
}