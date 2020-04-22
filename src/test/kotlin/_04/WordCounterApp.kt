package _04

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

fun WordCounterApp(): HttpHandler = { r: Request ->
    Response(OK).body(r.bodyString().split(" ").size.toString())
}