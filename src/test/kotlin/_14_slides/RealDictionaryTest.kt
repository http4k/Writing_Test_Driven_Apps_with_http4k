package _14_slides

import org.http4k.client.OkHttp
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters

class RealDictionaryTest : DictionaryContract {
    override val http = ClientFilters.SetBaseUriFrom(Uri.of("http://dictionary.com:12345")).then(OkHttp())
}