package dev.kavrin.boot4basics.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "quotes")
data class QuotesConfig(
    val search: SearchConfig = SearchConfig(),
    val validation: ValidationConfig = ValidationConfig()
) {
    data class SearchConfig(
        val ignoreCase: Boolean = true,
        val minLength: Int = 3,
    )

    data class ValidationConfig(
        val minLength: Int = 5,
        val maxLength: Int = 500,
        val requiredAuthor: Boolean = true,
        val allowedCategories: List<String> = emptyList(),
    )
}
