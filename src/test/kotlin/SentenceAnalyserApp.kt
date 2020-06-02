import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.http4k.format.Jackson.auto
import java.util.concurrent.atomic.AtomicInteger

fun SentenceAnalyserApp(dictionaryHttp: HttpHandler): HttpHandler {
    val dictionary = Dictionary(dictionaryHttp)
    val counter = AtomicInteger()
    return routes(
        "/count" bind Method.POST to CallCounter(counter).then { request: Request ->
            Response(Status.OK).body(dictionary.validWordsFrom(request).size.toString())
        },
        "/calls" bind Method.GET to { Response(Status.OK).body(counter.get().toString()) },
        "/analyse" bind Method.POST to { request: Request ->
            val lens = Body.auto<Analysis>().toLens()
            Response(Status.OK).with(lens of Analysis(
                request.bodyString().groupBy { it }.mapValues { it.value.size }
            ))
        }
    )
}

private fun Dictionary.validWordsFrom(request: Request) = request.bodyString().split(" ").filter(::isValid)

data class Analysis(val breakdown: Map<Char, Int>)

fun main() {
    SentenceAnalyserApp(FakeDictionary()).asServer(SunHttp(8080)).start()
}