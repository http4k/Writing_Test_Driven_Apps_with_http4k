package _14

class FakeDictionaryTest : DictionaryContract {
    override val http = FakeDictionary()
}