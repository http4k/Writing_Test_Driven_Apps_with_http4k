import org.http4k.client.OkHttp
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.junit.jupiter.api.Disabled

@Disabled
class RealDictionaryTest : DictionaryContract() {
    override val http = ClientFilters.SetBaseUriFrom(Uri.of("http://api.dictionary.com:10000")).then(OkHttp())
}
