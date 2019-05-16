package com.practice.moviedatabase

var numInt : Int = 0
var numFloat : Float = 0.0f

fun main(args : Array<String>) {
    main()
}

fun main() {
    println("Hello, World!")

    // Data Types
    dataTypes()
}

fun dataTypes() {
    numInt = 10
    numFloat = 12.4f

    println("$numInt $numFloat")

    println(switchOperation(numInt))
}

fun switchOperation(num : Int): String {
    return when(num) {
        0 -> "Number is zero"
        1, 2, 3, 4 -> "Number is between 1 to 4"
        10 -> "Number is Ten"
        else -> "Number is out-ranged"
    }
}
