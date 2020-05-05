package _03_1

import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun SentenceAnalyserServer() = SentenceAnalyserApp().asServer(SunHttp(8000))

/**
 * extract main and run (use idea plugin/term)
 */
fun main() {
    SentenceAnalyserServer().start()
}

