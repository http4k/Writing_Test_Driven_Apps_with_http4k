import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

fun FakeDictionary(): HttpHandler = routes("/{word}" bind Method.GET to { request: Request ->
    val words = setOf("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog")
    if (request.path("word") in words) Response(Status.OK) else Response(Status.NOT_FOUND)
})