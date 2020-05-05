package _03_1

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun SentenceAnalyserApp(): HttpHandler = { r: Request ->
    Response(OK).body(r.bodyString().split(" ").size.toString())
}

/**
 * extract main and run (use idea plugin/term)
 */
fun main() {
   SentenceAnalyserApp().asServer(SunHttp(8000)).start()
}

