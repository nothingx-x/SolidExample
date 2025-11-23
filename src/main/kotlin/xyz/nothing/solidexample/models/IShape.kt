package xyz.nothing.solidexample.models


interface IShape {
    fun getName(): String = ""
}
interface I2DShape : IShape{
    fun area(): Double
    fun perimeter(): Double
}

interface I3DShape : IShape{
    fun volume(): Double
    fun surfaceArea(): Double
}