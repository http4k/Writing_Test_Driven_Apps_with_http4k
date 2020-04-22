TESTING HTTP4K presentation

Write it in http4k to dump it - tests reusable!

## 0. Define HttpHandler and Filter

## 01. First endpoint "count words in sentence" + failing test
WordCounterTest
    code: endpoint POST /count - body is text
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
    code: endpoint GET /calls - return 0
    run: gets further but fails on second call
CallCounterTest
    test: `counts number of calls` calls through to stub handler twice and increments counter - fails
        expect: counter to equal 2
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
        
## 08. implement remote version of contract on dynamic port
WordCounterRemoteTest
    test: extend WordCounterContract
        convert app to server and start on port 8080
        add before and after methods to start and stop serve

## 09. use random port to allow parallelisation of tests
    test: convert test to use dynamic port

## 10. add analysing of sentence - test fails

## 11. implement analysing of sentence - lens! test passes

## 12. conversion of test to use approval testing lib

## 13. implement Dictionary Test and Dictionary client

## 14. extract dictionary contract, add fake dictionary and test contract implementation

## 15. use the dictionary in the WordCounter, and also change the tests to only expect valid words

## 16. add Chaos Engine and a new test to confirm what our client does when this happens.

## 17. add main method to convert fake into a real server and configure chaos within it. Demo with swagger...

## 18. Define ServiceVirtualisation. Add WordCounterClient and Contract interface

## 19. add recording & replaying contract

remote api driver - DSL

servirtium

