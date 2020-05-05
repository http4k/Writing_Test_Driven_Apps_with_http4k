package _05

import org.http4k.core.HttpHandler

interface Filter : (HttpHandler) -> HttpHandler