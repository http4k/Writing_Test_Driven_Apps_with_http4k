package _15

class FakeDictionaryTest : DictionaryContract {
    override val http = FakeDictionary()
}