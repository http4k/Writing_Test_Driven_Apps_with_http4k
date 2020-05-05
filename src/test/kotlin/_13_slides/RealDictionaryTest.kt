package _13_slides

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.client.OkHttp
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.junit.jupiter.api.Test

/**
 * Implement Dictionary Test and Dictionary client
 * (needs /etc/hosts hack and running server)
 *
 * CAT is not a valid word (service run by dogs)
 */
class RealDictionaryTest {
    private val http =
        ClientFilters.SetBaseUriFrom(Uri.of("http://dictionary.com:12345")).then(OkHttp())

    @Test
    fun `known word is valid`() {
        assertThat(Dictionary(http).isValid("the"), equalTo(true))
    }

    @Test
    fun `unknown word is not valid`() {
        assertThat(Dictionary(http).isValid("cat"), equalTo(false))
    }
}