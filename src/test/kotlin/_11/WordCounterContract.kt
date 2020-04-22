package _11

import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

/**
 * implement analysing of sentence - test passes
 */
interface WordCounterContract {
    val app: HttpHandler

    @Test
    fun `can count words`() {
        assertThat(app(Request(POST, "/count").body("the lazy lazy cat")), hasStatus(OK).and(hasBody("4")))
    }

    @Test
    fun `keeps track of total of calls`() {
        assertThat(app(Request(GET, "/calls")), hasStatus(OK).and(hasBody("0")))
        app(Request(POST, "/count").body("the lazy lazy cat"))
        assertThat(app(Request(GET, "/calls")), hasStatus(OK).and(hasBody("1")))
    }

    @Test
    fun `can analyse an empty sentence`() {
        val expected = """{"breakdown":{}}"""
        assertThat(app(Request(POST, "/analyse").body("")), hasStatus(OK).and(hasBody(expected)))
    }

    @Test
    fun `can analyse a sentence`() {
        val expected = """{"breakdown":{"t":2,"h":1,"e":1," ":3,"l":2,"a":3,"z":2,"y":2,"c":1}}"""
        assertThat(app(Request(POST, "/analyse").body("the lazy lazy cat")), hasStatus(OK).and(hasBody(expected)))
    }
}