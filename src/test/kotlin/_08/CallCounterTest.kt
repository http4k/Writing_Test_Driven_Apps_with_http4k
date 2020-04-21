package _08

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class CallCounterTest {
    @Test
    fun `counts number of calls`() {
        val count = AtomicInteger(0)
        val app = CallCounter(count).then { Response(Status.OK) }
        app(Request(Method.GET, ""))
        app(Request(Method.GET, ""))
        assertThat(count.get(), equalTo(2))
    }
}