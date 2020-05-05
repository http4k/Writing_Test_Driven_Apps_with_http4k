package _09

import org.http4k.client.OkHttp
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 * Use random port to allow parallelisation of tests
 */
class SentenceAnalyserRemoteTest : SentenceAnalyserContract {
    private val server = SentenceAnalyserServer(0).start()
    override val app by lazy { ClientFilters.SetBaseUriFrom(Uri.of("http://localhost:${server.port()}")).then(OkHttp()) }

    @BeforeEach
    fun start() {
        server.start()
    }

    @AfterEach
    fun stop() {
        server.stop()
    }
}