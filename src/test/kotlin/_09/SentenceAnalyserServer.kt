package _09

import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun SentenceAnalyserServer(port: Int) = SentenceAnalyserApp().asServer(SunHttp(port))

/**
 * extract main, extract port and run (use idea plugin/term)
 */
fun main() {
    SentenceAnalyserServer(8080).start()
}

