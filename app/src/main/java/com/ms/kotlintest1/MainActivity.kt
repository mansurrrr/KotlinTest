package com.ms.kotlintest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TestClass("123", 1).testIterators()
    }


    inner class TestClass(var mName: String, var mAge: Int) {
        /* Switch */
        var testStr = "test";
        init {
            testStr = mName;
        }
        fun testSwitch() {
            when (testStr) {
                "1", "2" -> println("1");
                else -> println("nothing");
            }
        }

        /* For */
        fun testFor() {
            test@for (i in 0..9) {
                for (j in 0..9) {
                    break@test;
                }
            }
        }

        /* Iterators  */
        fun testIterators() {
            var arrayList: ArrayList<Int> = arrayListOf();
            arrayList.add(1, 3);
            arrayList.add(2, 4);

            for (i in 0 until arrayList.size) {
                println("@{arrayList.get(i)}")
            }
            // todo arrayList 다른 api들 확인 (ex. 고차 for)
            arrayList.forEachIndexed { index, i ->
                println()
            }
        }

        /* Function Parameter */
        val testFun1: (String) -> Unit = { str -> println("$str abcddd")}
        fun testFunction() {
            a(this::b)
        }
        fun b(str: String) {
            println("alkjdsj")
        }
        fun a(function: (String) -> Unit) {
            function("abcd")
        }
    }
}