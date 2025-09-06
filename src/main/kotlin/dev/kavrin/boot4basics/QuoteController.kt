package dev.kavrin.boot4basics

import dev.kavrin.boot4basics.service.QuotesService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

//@Component
//class Test {
//    init {
//        println("Hello World")
//    }
//}

@RestController
@RequestMapping("/quotes")
class QuoteController(
    private val quotesService: QuotesService,
    private val restTemplate: RestTemplate,
//    private val test: Test
) {

//    @Autowired
//    lateinit var test: Test

    @GetMapping
    fun loadQuotes(
        @RequestParam("q", required = false) query: String?,
    ): List<QuoteDto> {
        return quotesService.loadQuotes(query)
    }

    @PostMapping
    fun postQuote(
        @Valid @RequestBody quoteDto: QuoteDto
    ): QuoteDto {
        return quotesService.insertQuote(quoteDto)
    }

    @PostMapping("/list")
    fun postListQuotes(
        @Valid @RequestBody quoteDtos: List<QuoteDto>
    ): List<QuoteDto> {
        return quotesService.insertQuotes(quoteDtos)
    }

    @PutMapping()
    fun putQuote(
        @RequestBody quote: QuoteDto
    ): QuoteDto {
        return quotesService.updateQuote(quote)
    }

    @DeleteMapping("/{id}")
    fun deleteQuote(
        @PathVariable("id") id: Long
    ): ResponseEntity<Void> {
        quotesService.deleteQuote(id)
        return ResponseEntity.noContent().build()

    }

}