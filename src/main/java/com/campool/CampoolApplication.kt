package com.campool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class CampoolApplication

fun main(args: Array<String>) {
    runApplication<CampoolApplication>(*args)
}

