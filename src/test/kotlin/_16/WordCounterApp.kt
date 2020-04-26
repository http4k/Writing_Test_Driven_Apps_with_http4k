package _16

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.routes
import java.util.concurrent.atomic.AtomicInteger

fun WordCounterApp(dictionaryHttp: HttpHandler): HttpHandler {
    val dictionary = Dictionary(dictionaryHttp)
    val counter = AtomicInteger()
    return routes(
        "/count" bind POST to
            CallCounter(counter).then { r: Request ->
                Response(OK).body(dictionary.validWordsFrom(r).size.toString())
            },
        "/calls" bind GET to {
            Response(OK).body(counter.get().toString())
        },
        "/analyse" bind POST to {
            val lens = Body.auto<Analysis>().toLens()

            Response(OK).with(lens of Analysis(
                dictionary.validWordsFrom(it).joinToString(" ")
                    .groupBy { it }
                    .mapValues { it.value.size }))
        }
    )
}

private fun Dictionary.validWordsFrom(r: Request) = r.bodyString().split(" ").filter(this::isValid)

data class Analysis(val breakdown: Map<Char, Int>)
