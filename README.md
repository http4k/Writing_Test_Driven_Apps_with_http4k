# Code for the Writing Test Driven Apps with http4k presentation

## Pre-requisites

To simulate the 3rd party dictionary service, we need to run the `DictionaryService` in its own terminal:

```bash
./gradlew runThirdPartyDictionary
```

We also need add a fake dns entry to `etc/hosts`:

```
127.0.0.1	localhost api.dictionary.com
``` 

## Building it

```bash
./gradlew check
```