package dev.kavrin.boot4basics.service

import dev.kavrin.boot4basics.QuoteDto
import dev.kavrin.boot4basics.QuotesNotFoundException
import dev.kavrin.boot4basics.config.QuotesConfig
import dev.kavrin.boot4basics.repository.QuotesRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
//@Profile("dev")
class QuotesService(
    private val quotesRepository: QuotesRepository,
    private val quotesConfig: QuotesConfig,
    @param:Value($$"${MY_CUSTOM_PROPERTY}") private val customVar: String
) {

    init {
        println("QuotesService initialized. Custom variable is: $customVar")
        println("QuotesConfig: ${quotesConfig.search}, ${quotesConfig.validation}")
    }
    fun loadQuotes(query: String?): List<QuoteDto> {
        if (query != null && query.length < quotesConfig.search.minLength) {
            throw IllegalArgumentException("Query length must be at least ${quotesConfig.search.minLength} characters")
        }
        return quotesRepository.loadQuotes(query)
    }
    fun insertQuote(quote: QuoteDto) = quotesRepository.postQuote(quote)
    fun insertQuotes(quotes: List<QuoteDto>) = quotesRepository.postListQuotes(quotes)
    fun updateQuote(quote: QuoteDto) = quotesRepository.putQuote(quote)
    fun deleteQuote(id: Long) {
        val deleted = quotesRepository.deleteQuote(id)
        if (!deleted) {
            throw QuotesNotFoundException(id)
        }

    }

}