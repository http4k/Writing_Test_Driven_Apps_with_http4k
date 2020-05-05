package _14_slides

class FakeDictionaryTest : DictionaryContract {
    override val http = FakeDictionary()
}