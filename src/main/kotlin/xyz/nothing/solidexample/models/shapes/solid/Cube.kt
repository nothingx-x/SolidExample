package xyz.nothing.solidexample.models.shapes.solid


import xyz.nothing.solidexample.models.I3DShape
import kotlin.math.pow

class Cube (val side: Double): I3DShape{
    override fun volume(): Double = side.pow(3)

    override fun surfaceArea(): Double = side.pow(2) * 6
}