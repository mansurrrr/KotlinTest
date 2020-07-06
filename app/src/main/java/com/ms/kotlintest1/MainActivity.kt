package com.ms.kotlintest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    interface ObjectTestListener {
        fun objectTestListener(count: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Test Data Class
     */
    data class TestDataClass(var mTitle: String, var mIndex: Int)

    /**
     * Test Functions
     */
    /* For1 */
    fun testForList() {
        val ary = arrayListOf<TestDataClass>(
            TestDataClass("a", 1),
            TestDataClass("b", 2),
            TestDataClass("c", 3)
        )
        for ((title, idx) in ary) {
            println("$title at $idx")
        }
    }

    fun testForMap() {
        val testMap = mutableMapOf("1" to "a", "2" to "b")
        testMap.put("1", "c")
        testMap.put("3", "k")
        for (entry in testMap) {
            println("${entry.key} :: ${entry.value}")
        }
    }

    fun testForAt() {
        testI@ for (i in 0..9) {
            testJ@ for (j in 0..9) {
                break@testI // break i
            }
        }
    }

    /* Iterators  */
    fun testIterators() {
        var arrayList: ArrayList<Int> = arrayListOf();
        arrayList.add(1, 3);
        arrayList.add(2, 4);

        for (i in 0 until arrayList.size) {
            println("${arrayList[i]}")
        }
        arrayList.forEachIndexed { index, i ->
            println("$index at $i")
        }
        arrayList.forEach { println("${it.compareTo(5)}") }
    }

    /* Switch */
    fun testSwitch(name: Any?) {
        when (name) {
            "1", "2" -> println("1 String")
            3 -> println("3 Int")
            else -> println("else")
        }
    }

    /* Function Parameter and lambda*/
    private val testFun1: (String) -> Unit = { str -> println("$str abcddd") }
    private fun testFunction() {
        a(this::b)
        returnACaller("returnACaller Test", this::returnA)
    }

    private fun b(str: String) {
        println("tesstB")
    }

    private fun a(function: (String) -> Unit) {
        function("testA")
    }

    private fun returnA(input: String): String = input
    private fun returnACaller(input: String, function: (String) -> String) {
        val returnStr = function(input)
        println(returnStr)
    }

    /* Scope test */
    fun scopeTest(): Unit {
        var scopeA = ReferClass("abcd").apply {
            mName = "1234"
            testFun()
        }
        scopeA.run {
            println(mName)
        }
    }

    /* object */
    fun objectTest() {
        println(TestUtils.tag)
        TestUtils.testA()
    }

    /* object listener */
    fun objectListenerTest() {
        objectListnerEvent(object : ObjectTestListener {
            override fun objectTestListener(count: Int) {
                println("abcd $count")
            }
        })
    }

    private fun objectListnerEvent(listener: ObjectTestListener?) {
        listener?.objectTestListener(1)
    }

    /* Poly */
    private fun testPoly() {
        val obj: BasePolyClass = ChildPolyClass()
        if (obj is ChildPolyClass) {
            obj.childFun()
        }
        val childObj = obj as ChildPolyClass
        childObj.childFun()
    }

    /* Generic */
    fun <T : BasePolyClass> genericTest(obj: T) {
        obj.testFun()
    }

    /* Collection */
    fun collectionTest() {
        var list = listOf<String>("1", "2", "3")
        var mutableList = mutableListOf<String>("1", "2", "3")
        for (str in list) {
            println(str)
        }
        mutableList.add(2, "3");
        mutableList.shuffle()
        for (str in mutableList) {
            println(str)
        }
        list.forEach { println(it) }
        println(list.map { it + "a" })
        println(list.filter { it.startsWith("1") })
        println(list.any { it.length == 3 })
        println(list.none { it.contains("k") })
        println(list.first { it.contains("1") })
        println(list.last { it.contains("1") })
        println(list.count { it.contentEquals("2") })
    }

    fun collectionTest2() {
        val aryA = arrayListOf<TestDataClass>(TestDataClass("1", 1), TestDataClass("2", 2))
        val mapA = aryA.associateBy { it.mIndex }
        val mapB = aryA.groupBy { it.mIndex }
        val (aryC, aryD) = aryA.partition { it.mIndex > 1 }
        val aryE = aryA.flatMap { listOf(it.mIndex * 2, it.mTitle + "hello") }

        val numbers = listOf<Int>(1, 2, 3)
        println(numbers.getOrElse(3) { 0 })
    }

    /* String */
    fun stringTest() {
        var strA: String? = null
        var strB: String? = " " // + carriage return, etc
        println(strA.isNullOrEmpty())
        println(strB.isNullOrBlank())
    }

    /* Null Test */
    fun nullTest() {
        val strA: String? = null
        println(strA?.toUpperCase(Locale.ENGLISH))
        println(strA ?: "Default".toUpperCase(Locale.ENGLISH))
        println(strA!!.toUpperCase(Locale.ENGLISH)) // exception

        // todo ANy?
        strA.run {
            println(toUpperCase(Locale.ENGLISH))
        }
    }

    fun nullTestWithClass() {
        var a = ReferClass("a")
        var b = ReferClass("b")
        var c = a
        println(a === b)
        println(a == c)
    }

    /* Arguments Test */
    fun paramsCaller() {
        argTest("a")
        argTest("a", bool = true)
        argTest("a", 2, true)
    }

    fun argTest(str: String, int: Int = 1, bool: Boolean = false) {
        println("${str}, ${int}, $bool")
    }

    fun argTest(vararg strList: String) {
        for (str in strList) println(str)
    }

    /* Infix */
    infix fun Int.multi(x: Int) = this * x
    fun infixTest() {
        println(6 multi 4)
        println(6.multi(4))
    }

    /* Compare Class */
    fun classCompareTest() {
        val a = TestDataClass("a", 1)
        var b = TestDataClass("b", 2)
        println(a == b)
        println(a == b.copy())
        println(a == b.copy(mTitle = "k"))
    }

    /* LateInit, Lazy */
    lateinit var testVariable: TestDataClass
    fun isInitlized(): Boolean = ::testVariable.isInitialized;
    val num: Int by lazy { 7 }

    /**
     * Test Refer Class
     */
    inner class ReferClass(var mName: String) {
        init {
            println("init")
        }

        fun testFun() = println(mName)

        // override equals
        override fun equals(other: Any?): Boolean {
            return if (other is ReferClass) {
                other.mName == this.mName
            } else {
                false
            }
        }

        // override hashCode
        override fun hashCode(): Int {
            return mName.hashCode()
        }
    }
}