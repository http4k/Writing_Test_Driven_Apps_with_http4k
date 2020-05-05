package _14_slides

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

fun FakeDictionary(): HttpHandler =
    routes("/{word}" bind GET to { r: Request ->
        val words = setOf("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog")
        if (r.path("word") in words) Response(OK) else Response(NOT_FOUND)
    })