package _18

import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.http4k.testing.Approver
import org.http4k.testing.JsonApprovalTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(JsonApprovalTest::class)
interface SentenceAnalyserContract {
    val app: HttpHandler

    @Test
    fun `can count valid words`() {
        assertThat(app(Request(POST, "/count").body("the lazy lazy cat")), hasStatus(OK).and(hasBody("3")))
    }

    @Test
    fun `keeps track of total of calls`() {
        assertThat(app(Request(GET, "/calls")), hasStatus(OK).and(hasBody("0")))
        app(Request(POST, "/count").body("the lazy lazy cat"))
        assertThat(app(Request(GET, "/calls")), hasStatus(OK).and(hasBody("1")))
    }

    @Test
    fun `can analyse an empty sentence`(approver: Approver) {
        approver.assertApproved(app(Request(POST, "/analyse").body("")))
    }

    @Test
    fun `can analyse a sentence`(approver: Approver) {
        approver.assertApproved(app(Request(POST, "/analyse").body("the lazy lazy cat")))
    }
}