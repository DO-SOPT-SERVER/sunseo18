package com.example.SeventhSeminarKotlin.Post.controller.dto.request

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [TitleValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidTitle(
    val message: String = "Invalid title",
    val pattern: String = "[가-힣|a-z|A-Z|0-9|]",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class TitleValidator : ConstraintValidator<ValidTitle, String> {

    private lateinit var pattern: String

    override fun initialize(constraintAnnotation: ValidTitle) {
        pattern = constraintAnnotation.pattern
    }

    override fun isValid(title: String?, context: ConstraintValidatorContext): Boolean {
        title ?: return false
        if (title.isBlank()) return false
        if (title.isEmpty() || title.length > 10) {
            return false
        }

        if (title.startsWith(' ')) return false

        return true
    }
}
