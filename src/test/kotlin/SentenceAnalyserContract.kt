import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.has
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.http4k.testing.Approver
import org.http4k.testing.JsonApprovalTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(JsonApprovalTest::class)
abstract class SentenceAnalyserContract {
    protected abstract val app: HttpHandler

    @Test
    fun `can count words`() {
        val request = Request(Method.POST, "/count").body("the lazy lazy cat")

        val response = app(request)

        assertThat(response, hasStatus(Status.OK) and hasBody("3"))
    }

    @Test
    fun `keeps track of total of calls`() {
        assertThat(app(Request(Method.GET, "/calls")), hasStatus(Status.OK) and hasBody("0"))

        app(Request(Method.POST, "/count").body("the lazy lazy cat"))

        assertThat(app(Request(Method.GET, "/calls")), hasStatus(Status.OK) and hasBody("1"))
    }

    @Test
    fun `can analyse an empty sentence`(approver: Approver) {
        approver.assertApproved(app(Request(Method.POST, "/analyse").body("")))
    }

    @Test
    fun `can analyse a sentence`(approver: Approver) {
        approver.assertApproved(app(Request(Method.POST, "/analyse").body("the lazy lazy cat")))
    }
}