package dev.kavrin.boot4basics

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Pattern(
    regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}",
    message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#\$%^&+=), with no whitespace"
)
annotation class Password(
    val message: String = "Invalid password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
