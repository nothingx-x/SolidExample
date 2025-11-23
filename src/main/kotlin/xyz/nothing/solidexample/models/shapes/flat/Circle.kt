package xyz.nothing.solidexample.models.shapes.flat

import xyz.nothing.solidexample.models.I2DShape
import kotlin.math.PI
import kotlin.math.pow


class Circle (val r: Double): I2DShape {
    override fun area(): Double = r.pow(2) * PI;
    override fun perimeter(): Double = r * 2
}