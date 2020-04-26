TESTING HTTP4K presentation

Write it in http4k to dump it - tests reusable!

## -01. Set the Dictionary Server running

## 00. Define HttpHandler and Filter

## 01. First endpoint "count words in sentence" + failing test
WordCounterTest
    code: HttpHandler POST /count - body is text = TODO()
    test: `can count words` in `the lazy lazy cat` 
        expect: using hamkrest - status OK and body = 4
    run: fails
     
## 02. implement endpoint - test passes
    code: implement inline function: split and populate body
    run: passes

## 03. convert to a "Server as a Function"
WordCounterApp
    refactor: extract function to it's own file
    run: passes
    
## 04. add new endpoint test - fails
    test: `keeps track of total of calls` by calling /calls then /count then /calls
        expect1: using hamkrest - status OK and body = 0
        expect2: using hamkrest - status OK and body = 1
    run: fails
    
## 05. Define filter. then implement call counter filter and test - app test still fails
    code: introduce routes() block - bind /count to POST
    run: passes
    code: add endpoint GET /calls - return 0
    run: gets further but fails on second call

CallCounterTest
    test: `counts number of calls` calls through to stub handler and increments counter - fails
        expect: counter to equal 1
CallCounter
    code: function CallCounter() - returns empty filter
    run: filter test fails
    code: filter CallCounter - introduce counter and increment it - test now passes
    run: endpoint test still fails
    
## 06. use call counter in app - tests now pass
    code: wrap /count in filter, taking atomic counter
    code: return counter content
    run: passes

## 07. extract common test interface for shared test
    refactor: convert app to field
WordCounterContract
    refactor: extract interface, leaving val abstract
    run: passes
        
## 08. implement remote version of contract on dynamic port
WordCounterRemoteTest
    test: extend WordCounterContract
        convert app to server and start on port 8080
        add before and after methods to start and stop serve
        app by lazy and preset localhost and port with SetBaseUriFrom 
        use OkHttp
    run: passes

## 09. use random port to allow parallelisation of tests
    test: convert test to use dynamic port 0
    run: passes

## 10. add analysing of sentence - test fails
    test: `can analyse a sentence` POST to /analyze
        input: "the lazy lazy cat"
        expected: OK && {"breakdown":{"t":2,"h":1,"e":1," ":3,"l":2,"a":3,"z":2,"y":2,"c":1}} 
    run: fails

## 11. implement analysing of sentence - test passes
    code: 
        introduce Analysis, data class with Map<Char, Int>
        create Analysis with emptyMap()
        use lens to respond
    run: fails
    code: 
    grouping: take body, group by first letter, map to size of list

## 12. conversion of test to use approval testing lib
    code: extend JsonApprovalTest
        introduce approver and convert tests to use it
    run: fails
    IDE: approve tests
    run: passes
            
## 13. implement Dictionary Test and Dictionary client
RealDictionaryTest
    test: `known word is valid` to test isValid() in client
    code: create Dictionary(httpHandler).isValid(word)
        uses "SetBaseUri" again to set localhost:10000
    test: `unknown word is not valid` to test isValid() in client
    run: passes

## 14. extract dictionary contract, add fake dictionary and test contract implementation.
    refactor: extract DictionaryContract interface, leave http abstract
FakeDictionaryTest
    test: add FakeDictionary : HttpHandler
    code: implement FakeDictionary
        /{word} GET to Response(OK)
    run: fails
    code:
        valid words "the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"
        if path word in words then OK else NOT_FOUND
    run: passes
    code: implement FakeDictionary
        /{word} GET to Response(OK)


## 15. use the dictionary in the WordCounter, also change the tests to only expect valid words
    refactor: rename `can count words to `can count valid words`
        expect: using hamkrest - status OK and body = 3
    run: fails
    test: add FakeDictionary() to WordCounterApp constructor - is HttpHandler
    run: fails
    code: wrap dictionaryHttp in Dictionary inside WordCounterApp
        introduce extension function Dictionary.validWordsFrom - split body by " " and map each word with isValid()
        use validWordsFrom in count
    run: pass
    code: use validWordsFrom in analyse
    run: fails
    IDE: approve tests
    run: pass
    
## 16. add Chaos Engine and a new test to confirm what our client does when this happens.

## 17. add main method to convert fake into a real server and configure chaos within it. Demo with swagger...

## 18. Define ServiceVirtualisation. Add WordCounterClient and Contract interface

## 19. add recording & replaying contract

remote api driver - DSL

servirtium

