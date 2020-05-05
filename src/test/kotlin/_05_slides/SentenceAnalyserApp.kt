package _05_slides

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer


fun SentenceAnalyserApp(): HttpHandler = routes(
    "/count" bind Method.POST to
        { r: Request ->
            Response(OK).body(r.bodyString().split(" ").size.toString())
        },
    "/calls" bind Method.GET to {
        Response(OK).body(0.toString())
    }
)

fun main() {
    _03.SentenceAnalyserApp().asServer(SunHttp(8000)).start()
}