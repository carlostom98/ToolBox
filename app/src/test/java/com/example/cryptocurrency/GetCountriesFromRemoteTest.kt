package com.example.cryptocurrency

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GetCountriesFromRemoteTest {

    private lateinit var bo: ByteArrayOutputStream


    @Before
    fun setUp() {
        bo = ByteArrayOutputStream()
        System.setOut(PrintStream(bo))
    }


    @Test
    fun testThePrintln() {
        println("Holaa")
        val lines = String(bo.toByteArray())
        assert(lines.contains("Holaa"))
    }
}