package _18

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters.SetBaseUriFrom

class Dictionary(http: HttpHandler) {
    private val dictionary = SetBaseUriFrom(Uri.of("http://localhost:10000"))
        .then(http)

    fun isValid(word: String): Boolean = when (dictionary(Request(GET, "/$word")).status) {
        OK -> true
        else -> false
    }
}