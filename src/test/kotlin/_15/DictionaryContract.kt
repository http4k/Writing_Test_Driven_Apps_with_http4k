package _15

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.HttpHandler
import org.junit.jupiter.api.Test

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