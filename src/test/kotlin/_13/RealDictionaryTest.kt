package _13

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.client.OkHttp
import org.junit.jupiter.api.Test

/**
 * Implement Dictionary Test and Dictionary client
 */
class RealDictionaryTest {
    private val http = OkHttp()

    @Test
    fun `known word is valid`() {
        assertThat(Dictionary(http).isValid("the"), equalTo(true))
    }

    @Test
    fun `unknown word is not valid`() {
        assertThat(Dictionary(http).isValid("foobar"), equalTo(false))
    }
}