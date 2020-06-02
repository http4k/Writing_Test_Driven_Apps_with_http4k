import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status

class Dictionary(private val http: HttpHandler) {
    fun  isValid(word: String): Boolean = when(http(Request(Method.GET, "/$word")).status){
        Status.OK -> true
        else -> false
    }
}