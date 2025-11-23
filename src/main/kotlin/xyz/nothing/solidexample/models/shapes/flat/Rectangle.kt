package xyz.nothing.solidexample.models.shapes.flat

import xyz.nothing.solidexample.models.I2DShape


class Rectangle(val width: Double, val height: Double) : I2DShape {
    override fun area(): Double = width * height
    override fun perimeter(): Double = 2 * (width + height)
}