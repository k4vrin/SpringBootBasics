package dev.kavrin.boot4basics

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfig {

    @Bean
    @Primary
    fun restTemplate(): RestTemplate {
        return RestTemplate().apply {
            errorHandler = ResponseErrorHandler { response ->
                response.body.use {
                    it.readBytes().decodeToString().startsWith("ERROR")
                }
            }
        }
    }

    @Bean
    fun restTemplate2(): RestTemplate {
        return RestTemplate()
    }
}