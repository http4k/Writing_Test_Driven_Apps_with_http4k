package _19

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.format.Jackson.auto
import org.http4k.junit.ServirtiumRecording
import org.http4k.junit.ServirtiumReplay
import org.http4k.servirtium.InteractionStorage.Companion.Disk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.io.File

/**
 * Add SentenceAnalyserClient and Contract interface
 */
class SentenceAnalyserClient(private val http: HttpHandler) {
    fun count(name: String): Int = http(Request(POST, "/count").body(name)).bodyString().toInt()

    fun analyse(name: String): Analysis =
        Body.auto<Analysis>().toLens()(http(Request(POST, "/analyse").body(name)))
}

interface SentenceAnalyserApiContract {
    @Test
    fun `can count valid words in a sentence`(http: HttpHandler) {
        assertThat(SentenceAnalyserClient(http).count("the lazy lazy cat"), equalTo(3))
    }

    @Test
    fun `can analyse valid words in a sentence`(http: HttpHandler) {
        assertThat(SentenceAnalyserClient(http).analyse("the lazy lazy cat").breakdown['y'], equalTo(2))
    }
}

/**
 * Add recording & replaying contract
 */
class RecordingSentenceAnalyserApiTest : SentenceAnalyserApiContract {

    private val app = SentenceAnalyserApp(FakeDictionary())

    @JvmField
    @RegisterExtension
    val record = ServirtiumRecording("SentenceAnalyser", app, Disk(File(".")))
}

class ReplayingSentenceAnalyserApiTest : SentenceAnalyserApiContract {

    @JvmField
    @RegisterExtension
    val record = ServirtiumReplay("SentenceAnalyser", Disk(File(".")))
}
