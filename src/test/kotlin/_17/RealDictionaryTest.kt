package _17

import org.http4k.client.OkHttp

class RealDictionaryTest : DictionaryContract {
    override val http = OkHttp()
}