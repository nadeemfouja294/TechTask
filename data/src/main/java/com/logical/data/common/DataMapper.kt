package com.logical.data.common

/**
 * This abstract class provides a blueprint for mapping API models to database entities.
 * It includes a method for performing the mapping and handling any exceptions that may occur.
 */
abstract class ApiToDatabaseMapper<INPUT : Any, OUTPUT : Any> {

    fun toDatabase(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DatabaseMapperException(
            "Could not map ${input::class.simpleName} to Database",
            throwable
        )
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

class DatabaseMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

/**
 * This abstract class provides a blueprint for mapping API models to domain models.
 * It includes a method for performing the mapping and handling any exceptions that may occur.
 */
abstract class ApiToDomainMapper<INPUT : Any, OUTPUT : Any> {
    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException("Could not map ${input::class.simpleName} to Domain", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

class DomainMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

/**
 * This abstract class provides a blueprint for mapping database entities to domain models.
 * It includes a method for performing the mapping and handling any exceptions that may occur.
 */
abstract class DatabaseToDomainMapper<INPUT : Any, OUTPUT : Any> {

    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException(
            "Could not map ${input::class.simpleName} to Domain",
            throwable
        )
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

/**
 * This abstract class provides a blueprint for mapping domain models to presentation models.
 * It includes a method for performing the mapping and handling any exceptions that may occur.
 */
abstract class DomainToPresentationMapper<INPUT : Any, OUTPUT : Any> {

    fun toPresentation(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

/**
 * This abstract class provides a blueprint for mapping presentation models to UI models.
 * It includes a method for performing the mapping and handling any exceptions that may occur.
 */
abstract class PresentationToUiMapper<INPUT : Any, OUTPUT : Any> {

    fun toUi(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw UiMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

class UiMapperException(message: String, throwable: Throwable?) : Exception(message, throwable)