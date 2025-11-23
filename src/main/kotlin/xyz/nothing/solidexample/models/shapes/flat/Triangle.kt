package xyz.nothing.solidexample.models.shapes.flat

import xyz.nothing.solidexample.models.I2DShape


class Triangle (val base: Double, val height: Double) : I2DShape {
    override fun area(): Double = .5 * base * height;
    override fun perimeter(): Double = base + height
}