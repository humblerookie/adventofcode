package com.advcode.core

import java.io.File

fun readFile(file:String) = File(file).readText()