package com.ms.kotlintest1

class ChildPolyClass: BasePolyClass() {
    override fun testFun() {
        println("From ChildPolyClass")
    }
    public fun childFun() {
        println("From Child only")
    }
}