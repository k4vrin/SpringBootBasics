package dev.kavrin.boot4basics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Boot4BasicsApplication

fun main(args: Array<String>) {
    runApplication<Boot4BasicsApplication>(*args)
}
