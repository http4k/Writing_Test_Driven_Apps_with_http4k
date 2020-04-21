package _16

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class FakeDictionaryTest : DictionaryContract {
    override val http = FakeDictionary()

    /**
     * This test is not possible with the "real" service
     */
    @Test
    fun `when blows up return false`() {
        http.blowUpOnce()
        assertThat(Dictionary(http).isValid("the"), equalTo(false))
        assertThat(Dictionary(http).isValid("the"), equalTo(true))
    }
}