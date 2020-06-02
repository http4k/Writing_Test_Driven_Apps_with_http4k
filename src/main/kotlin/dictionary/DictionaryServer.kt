package dictionary

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.SunHttp
import org.http4k.server.asServer

class DictionaryServer(port: Int) : Http4kServer by DebuggingFilters.PrintRequestAndResponse().then(routes).asServer(SunHttp(port))

private val routes = routes(
    "/{word}" bind GET to { r: Request ->
        when {
            r.path("word")?.trim() in words -> Response(OK)
            else -> Response(NOT_FOUND)
        }
    }
)
private val words = setOf("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog")

fun main() {
    DictionaryServer(10000).start()
    println("3rd Party Dictionary started. Leave this terminal running while running other tests...")
}