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
 * Add WordCounterClient and Contract interface
 */
class WordCounterClient(private val http: HttpHandler) {
    fun count(name: String): Int = http(Request(POST, "/count").body(name)).bodyString().toInt()

    fun analyse(name: String): Analysis =
        Body.auto<Analysis>().toLens()(http(Request(POST, "/analyse").body(name)))
}

interface WordCounterApiContract {
    @Test
    fun `can count valid words in a sentence`(http: HttpHandler) {
        assertThat(WordCounterClient(http).count("the lazy lazy cat"), equalTo(3))
    }

    @Test
    fun `can analyse valid words in a sentence`(http: HttpHandler) {
        assertThat(WordCounterClient(http).analyse("the lazy lazy cat").breakdown['y'], equalTo(2))
    }
}

/**
 * Add recording & replaying contract
 */
class RecordingWordCounterApiTest : WordCounterApiContract {

    private val app = WordCounterApp(FakeDictionary())

    @JvmField
    @RegisterExtension
    val record = ServirtiumRecording("WordCounter", app, Disk(File(".")))
}

class ReplayingWordCounterApiTest : WordCounterApiContract {

    @JvmField
    @RegisterExtension
    val record = ServirtiumReplay("WordCounter", Disk(File(".")))
}
