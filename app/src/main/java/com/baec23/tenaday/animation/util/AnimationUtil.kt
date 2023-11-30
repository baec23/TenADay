package com.baec23.tenaday.animation.util

data class Point(val x: Float, val y: Float)

fun getCirclePoints(numPoints: Int, centerX: Int, centerY: Int, radius: Int): List<Point> {
    val toReturn = mutableListOf<Point>()
    val angleStep = 2 * Math.PI / numPoints
    for (i in 0 until numPoints) {
        val angle = i * angleStep
        val x = centerX + radius * Math.cos(angle).toFloat()
        val y = centerY + radius * Math.sin(angle).toFloat()
        toReturn.add(Point(x, y))
    }
    return toReturn.toList()
}
