import org.http4k.core.HttpHandler

class FakeDictionaryTest : DictionaryContract() {
    override val http: HttpHandler = FakeDictionary()
}

