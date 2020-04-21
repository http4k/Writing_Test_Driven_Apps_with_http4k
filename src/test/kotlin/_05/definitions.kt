package _05

import org.http4k.core.Request
import org.http4k.core.Response

typealias HttpHandler = (Request) -> Response

interface Filter : (HttpHandler) -> HttpHandler