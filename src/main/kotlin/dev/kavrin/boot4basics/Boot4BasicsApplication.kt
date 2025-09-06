package dev.kavrin.boot4basics

import dev.kavrin.boot4basics.config.QuotesConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(QuotesConfig::class)
class Boot4BasicsApplication

fun main(args: Array<String>) {
    runApplication<Boot4BasicsApplication>(*args)
}
