package _08

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import java.util.concurrent.atomic.AtomicInteger

fun SentenceAnalyserApp(): HttpHandler {
    val counter = AtomicInteger()
    return routes(
        "/count" bind POST to
            CallCounter(counter).then { r: Request ->
                Response(OK).body(r.bodyString().split(" ").size.toString())
            },
        "/calls" bind GET to {
            Response(OK).body(counter.get().toString())
        }
    )
}

fun main() {
    SentenceAnalyserApp().asServer(SunHttp(8000)).start()
}