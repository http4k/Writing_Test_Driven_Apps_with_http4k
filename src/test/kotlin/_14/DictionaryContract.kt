package _14

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.HttpHandler
import org.junit.jupiter.api.Test

/**
 * Extract dictionary contract, add fake dictionary and test contract implementation
 */
interface DictionaryContract {
    val http: HttpHandler

    @Test
    fun `known word is valid`() {
        assertThat(Dictionary(http).isValid("the"), equalTo(true))
    }

    @Test
    fun `unknown word is not valid`() {
        assertThat(Dictionary(http).isValid("foobar"), equalTo(false))
    }
}