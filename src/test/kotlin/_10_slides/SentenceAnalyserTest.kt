package _10_slides

import org.http4k.core.HttpHandler

class SentenceAnalyserTest : SentenceAnalyserContract {
    override val app: HttpHandler = SentenceAnalyserApp()
}