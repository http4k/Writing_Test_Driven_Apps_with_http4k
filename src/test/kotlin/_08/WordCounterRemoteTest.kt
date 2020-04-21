package _08

import org.http4k.client.OkHttp
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 * implement remote version of contract - static port
 */
class WordCounterRemoteTest : WordCounterContract {
    private val server = WordCounterApp().asServer(SunHttp(8080))

    override val app = ClientFilters.SetBaseUriFrom(Uri.of("http://localhost:8080")).then(OkHttp())

    @BeforeEach
    fun start() {
        server.start()
    }

    @AfterEach
    fun stop() {
        server.stop()
    }
}