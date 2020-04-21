package _17

import org.http4k.chaos.ChaosBehaviours.ReturnStatus
import org.http4k.chaos.ChaosEngine
import org.http4k.chaos.ChaosTriggers.Once
import org.http4k.chaos.appliedWhen
import org.http4k.chaos.withChaosApi
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.I_M_A_TEAPOT
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

/**
 * Add Chaos Engine and a new test to confirm what our client does when this happens.
 */
class FakeDictionary : HttpHandler {
    private val chaosEngine = ChaosEngine()

    private val app =
        routes("/{word}" bind GET to { r: Request ->
            val words = setOf("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog")
            if (r.path("word") in words) Response(OK) else Response(NOT_FOUND)
        }).withChaosApi(chaosEngine)

    fun blowUpOnce() {
        chaosEngine.enable(ReturnStatus(I_M_A_TEAPOT).appliedWhen(Once()))
    }

    override fun invoke(p1: Request) = app(p1)
}

/**
 * Add main method to convert fake into a real server and configure chaos within it.
 */
fun main() {
    val fakeDictionary = FakeDictionary()
    fakeDictionary.asServer(SunHttp(10000)).start()
    fakeDictionary.blowUpOnce()
    println("Fake Dictionary is running. Go to: https://www.http4k.org/openapi3/?url=http://localhost:10000/chaos")
}