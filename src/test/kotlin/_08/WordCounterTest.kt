package _08

import org.http4k.core.HttpHandler

class WordCounterTest : WordCounterContract {
    override val app: HttpHandler = WordCounterApp()
}