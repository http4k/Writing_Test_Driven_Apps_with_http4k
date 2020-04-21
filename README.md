TESTING HTTP4K presentation

Write it in http4k to dump it - tests reusable!

00. Define HttpHandler
01. start point - failing test
02. implement endpoint - test passes
03. extract app
04. add new test - fails
05. Define filter. then implement call counter filter and test - app test still fails
06. use call counter in app - tests now pass
07. extract common test interface for shared test
08. implement remote version of contract
09. use random port to allow parallelisation of tests
10. add analysing of sentence - test fails
11. implement analysing of sentence - lens! test passes
12. conversion of test to use approval testing lib
13. implement Dictionary Test and Dictionary client
14. extract dictionary contract, add fake dictionary and test contract implementation
15. use the dictionary in the WordCounter, and also change the tests to only expect valid words
16. add Chaos Engine and a new test to confirm what our client does when this happens.
17. add main method to convert fake into a real server and configure chaos within it. Demo with swagger...
18. Define ServiceVirtualisation. Add WordCounterClient and Contract interface
19. add recording & replaying contract

remote api driver - DSL

servirtium

