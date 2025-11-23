package xyz.nothing.solidexample.models.shapes.flat

import xyz.nothing.solidexample.models.I2DShape
import kotlin.math.pow


class Square (val s: Double) : I2DShape {
    override fun area(): Double = s.pow(2)
    override fun perimeter(): Double = s * 4
}