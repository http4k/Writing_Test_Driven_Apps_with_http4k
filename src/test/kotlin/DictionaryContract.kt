import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.HttpHandler
import org.junit.jupiter.api.Test

abstract class DictionaryContract {
    protected abstract val http: HttpHandler

    @Test
    fun `known word is valid`() {
        assertThat(Dictionary(http).isValid("the"), equalTo(true))
    }

    @Test
    fun `known word is invalid`() {
        assertThat(Dictionary(http).isValid("foo"), equalTo(false))
    }
}