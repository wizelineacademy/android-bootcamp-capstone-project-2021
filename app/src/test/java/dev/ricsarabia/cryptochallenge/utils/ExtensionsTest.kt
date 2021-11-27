package dev.ricsarabia.cryptochallenge.utils

import org.junit.Assert.*
import org.junit.Test
import java.time.*
import java.time.format.DateTimeFormatter

/**
 * Created by Ricardo Sarabia on 2021/11/26.
 */
class ExtensionsTest {
    @Test
    fun `asDecimalAmount formats valid string numbers`() {
        // Given
        val numberWithoutZeros = "10"
        val numberWithZeros = "10.00000000"
        val numberWithDecimals = "10.00030000"
        val numberWithManyDecimals = "10.00000000001"
        val numberWithOneDecimal = "10.1"

        // Then
        assertEquals("10.00", numberWithoutZeros.asDecimalAmount())
        assertEquals("10.00", numberWithZeros.asDecimalAmount())
        assertEquals("10.0003", numberWithDecimals.asDecimalAmount())
        assertEquals("10.00", numberWithManyDecimals.asDecimalAmount())
        assertEquals("10.10", numberWithOneDecimal.asDecimalAmount())
    }

    @Test
    fun `asDecimalAmount formats invalid string numbers`() {
        // Given
        val invalidNumber = "Not a number"
        val emptyString = ""

        // Then
        assertEquals("0.00", invalidNumber.asDecimalAmount())
        assertEquals("0.00", emptyString.asDecimalAmount())
    }

    @Test
    fun `asDecimalPrice formats valid string numbers`() {
        // Given
        val numberWithoutZeros = "10"
        val numberWithZeros = "10.00000000"
        val numberWithDecimals = "10.00030000"
        val numberWithManyDecimals = "10.00000000001"
        val numberWithOneDecimal = "10.1"

        // Then
        assertEquals("$ 10.00", numberWithoutZeros.asDecimalPrice())
        assertEquals("$ 10.00", numberWithZeros.asDecimalPrice())
        assertEquals("$ 10.0003", numberWithDecimals.asDecimalPrice())
        assertEquals("$ 10.00", numberWithManyDecimals.asDecimalPrice())
        assertEquals("$ 10.10", numberWithOneDecimal.asDecimalPrice())
    }

    @Test
    fun `asDecimalPrice formats invalid string numbers`() {
        // Given
        val invalidNumber = "Not a number"
        val emptyString = ""

        // Then
        assertEquals("$ 0.00", invalidNumber.asDecimalPrice())
        assertEquals("$ 0.00", emptyString.asDecimalPrice())
    }

    @Test
    fun `asLocalDate formats date at specific zone`() {
        // Given
        val bitsoDate = "2021-11-26T19:38:00+00:00"

        // When
        val estOffset = ZoneOffset.of("-05:00")
        val cstOffset = ZoneOffset.of("-06:00")
        val mstOffset = ZoneOffset.of("-07:00")
        val pstOffset = ZoneOffset.of("-08:00")

        // Then
        assertEquals("26 Nov 2021, 14:38:00", bitsoDate.asLocalDate(estOffset))
        assertEquals("26 Nov 2021, 13:38:00", bitsoDate.asLocalDate(cstOffset))
        assertEquals("26 Nov 2021, 12:38:00", bitsoDate.asLocalDate(mstOffset))
        assertEquals("26 Nov 2021, 11:38:00", bitsoDate.asLocalDate(pstOffset))
    }

    @Test
    fun `asLocalDate formats date at local zone`() {
        // Given
        val date = OffsetDateTime.now()
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        val outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm:ss")

        // When
        val inputDate = date.format(inputFormat)
        val expectedDate = date.format(outputFormat)

        // Then
        assertEquals(expectedDate, inputDate.asLocalDate())
    }

    @Test
    fun `asLocalDate formats invalid date`() {
        // Given
        val emptyString = ""
        val badFormattedDate = "2021-11-26T19:38:00"

        // Then
        assertEquals("never", emptyString.asLocalDate())
        assertEquals("never", badFormattedDate.asLocalDate())
    }
}