package xyz.nothing.solidexample

import xyz.nothing.solidexample.models.I2DShape
import xyz.nothing.solidexample.models.shapes.flat.Circle
import xyz.nothing.solidexample.models.shapes.flat.Rectangle
import xyz.nothing.solidexample.models.shapes.flat.Square
import xyz.nothing.solidexample.models.shapes.flat.Triangle

val shapes = listOf<I2DShape>(
    Circle(4.0),
    Rectangle(2.0, 0.5),
    Square(2.0),
    Triangle(4.0, 9.0)
)

val options = mutableMapOf<Int, I2DShape>().apply {
    shapes.forEachIndexed { index, shape ->
        put(index + 1, shape) // Start from 1 for user-friendly display
    }
}

var operation = ""
val pickedShapes = mutableListOf<I2DShape>()

var state = "pick_shape"

fun main(args: Array<out String>) {
    println("=== Shape Operation Calculator ===")

    while (true) {
        when (state) {
            "pick_shape" -> pickShape()
            "pick_operation" -> pickOperation()
            "show_result" -> showResult()
            "exit" -> break
        }
    }
}

private fun pickShape() {
    if (pickedShapes.size == 2) {
        state = "pick_operation"
        return
    }

    println("\nSelect shape ${pickedShapes.size + 1} to perform an operation:")
    println("(Available operations: addition, subtraction, multiplication, division)")

    displayOptions()
    println("-".repeat(30))
    println("0 - Exit program")
    readPickShapeInput()
}

private fun readPickShapeInput() {
    val input = readlnOrNull()?.trim()

    when {
        input == "0" -> {
            state = "exit"
            return
        }
        input.isNullOrEmpty() -> {
            println("Please enter a valid number")
            return
        }
        else -> {
            try {
                val picked = input.toInt()
                val shape = options[picked]

                if (shape != null) {
                    pickedShapes.add(shape)
                    println("✓ Selected: ${shape.getName()} (Area: ${"%.2f".format(shape.area())}, Perimeter: ${"%.2f".format(shape.perimeter())})")

                    if (pickedShapes.size == 2) {
                        state = "pick_operation"
                    }
                } else {
                    println("Invalid selection. Please choose from the available options.")
                }
            } catch (e: NumberFormatException) {
                println("Please enter a valid number")
            }
        }
    }
}

private fun displayOptions() {
    println("\nAvailable shapes:")
    options.forEach { (key, shape) ->
        println("$key - ${shape.getName()} (Area: ${"%.2f".format(shape.area())}, Perimeter: ${"%.2f".format(shape.perimeter())})")
    }
}

private fun pickOperation() {
    println("\n=== Operation Selection ===")
    println("Selected shapes:")
    pickedShapes.forEachIndexed { index, shape ->
        println("  Shape ${index + 1}: ${shape.getName()} - Area: ${"%.2f".format(shape.area())}, Perimeter: ${"%.2f".format(shape.perimeter())}")
    }

    println("\nChoose an operation:")
    println("1 - Addition (+)")
    println("2 - Subtraction (-)")
    println("3 - Multiplication (×)")
    println("4 - Division (÷)")
    println("5 - Compare Areas")
    println("6 - Compare Perimeters")
    println("0 - Back to shape selection")

    print("Enter your choice: ")
    readOperationInput()
}

private fun readOperationInput() {
    val input = readlnOrNull()?.trim()

    when (input) {
        "0" -> {
            pickedShapes.clear()
            state = "pick_shape"
            return
        }
        "1" -> performOperation("addition")
        "2" -> performOperation("subtraction")
        "3" -> performOperation("multiplication")
        "4" -> performOperation("division")
        "5" -> compareAreas()
        "6" -> comparePerimeters()
        else -> {
            println("Invalid operation. Please try again.")
            pickOperation()
        }
    }
}

private fun performOperation(op: String) {
    val shape1 = pickedShapes[0]
    val shape2 = pickedShapes[1]
    val area1 = shape1.area()
    val area2 = shape2.area()
    val perimeter1 = shape1.perimeter()
    val perimeter2 = shape2.perimeter()

    println("\n=== Operation Result ===")
    println("Shape 1: ${shape1.getName()} - Area: ${"%.2f".format(area1)}, Perimeter: ${"%.2f".format(perimeter1)}")
    println("Shape 2: ${shape2.getName()} - Area: ${"%.2f".format(area2)}, Perimeter: ${"%.2f".format(perimeter2)}")
    println("Operation: $op")
    println("-".repeat(30))

    when (op) {
        "addition" -> {
            println("Area Result: ${"%.2f".format(area1)} + ${"%.2f".format(area2)} = ${"%.2f".format(area1 + area2)}")
            println("Perimeter Result: ${"%.2f".format(perimeter1)} + ${"%.2f".format(perimeter2)} = ${"%.2f".format(perimeter1 + perimeter2)}")
        }
        "subtraction" -> {
            println("Area Result: ${"%.2f".format(area1)} - ${"%.2f".format(area2)} = ${"%.2f".format(area1 - area2)}")
            println("Perimeter Result: ${"%.2f".format(perimeter1)} - ${"%.2f".format(perimeter2)} = ${"%.2f".format(perimeter1 - perimeter2)}")
        }
        "multiplication" -> {
            println("Area Result: ${"%.2f".format(area1)} × ${"%.2f".format(area2)} = ${"%.2f".format(area1 * area2)}")
            println("Perimeter Result: ${"%.2f".format(perimeter1)} × ${"%.2f".format(perimeter2)} = ${"%.2f".format(perimeter1 * perimeter2)}")
        }
        "division" -> {
            if (area2 != 0.0) {
                println("Area Result: ${"%.2f".format(area1)} ÷ ${"%.2f".format(area2)} = ${"%.2f".format(area1 / area2)}")
            } else {
                println("Area Result: Cannot divide by zero")
            }
            if (perimeter2 != 0.0) {
                println("Perimeter Result: ${"%.2f".format(perimeter1)} ÷ ${"%.2f".format(perimeter2)} = ${"%.2f".format(perimeter1 / perimeter2)}")
            } else {
                println("Perimeter Result: Cannot divide by zero")
            }
        }
    }

    state = "show_result"
}

private fun compareAreas() {
    val shape1 = pickedShapes[0]
    val shape2 = pickedShapes[1]
    val area1 = shape1.area()
    val area2 = shape2.area()

    println("\n=== Area Comparison ===")
    println("${shape1.getName()}: ${"%.2f".format(area1)}")
    println("${shape2.getName()}: ${"%.2f".format(area2)}")

    when {
        area1 > area2 -> println("${shape1.getName()} has LARGER area")
        area1 < area2 -> println("${shape2.getName()} has LARGER area")
        else -> println("Both shapes have EQUAL area")
    }

    state = "show_result"
}

private fun comparePerimeters() {
    val shape1 = pickedShapes[0]
    val shape2 = pickedShapes[1]
    val perimeter1 = shape1.perimeter()
    val perimeter2 = shape2.perimeter()

    println("\n=== Perimeter Comparison ===")
    println("${shape1.getName()}: ${"%.2f".format(perimeter1)}")
    println("${shape2.getName()}: ${"%.2f".format(perimeter2)}")

    when {
        perimeter1 > perimeter2 -> println("${shape1.getName()} has LONGER perimeter")
        perimeter1 < perimeter2 -> println("${shape2.getName()} has LONGER perimeter")
        else -> println("Both shapes have EQUAL perimeter")
    }

    state = "show_result"
}

private fun showResult() {
    println("\n" + "=".repeat(40))
    println("What would you like to do next?")
    println("1 - Perform another operation with same shapes")
    println("2 - Start over with new shapes")
    println("0 - Exit")
    print("Enter your choice: ")

    when (readlnOrNull()?.trim()) {
        "1" -> state = "pick_operation"
        "2" -> {
            pickedShapes.clear()
            state = "pick_shape"
        }
        "0" -> state = "exit"
        else -> {
            println("Invalid choice. Starting over...")
            pickedShapes.clear()
            state = "pick_shape"
        }
    }
}