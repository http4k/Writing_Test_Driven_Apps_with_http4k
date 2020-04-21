package _01

import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.HttpHandler
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

/**
 * start point - failing test
 */
class WordCounterTest {

    @Test
    fun `can count words`() {
        val app = makeApp()
        assertThat(app(Request(POST, "/count").body("the lazy lazy cat")), hasStatus(OK).and(hasStatus(OK).and(hasBody("4"))))
    }

    private fun makeApp(): HttpHandler = TODO()
}