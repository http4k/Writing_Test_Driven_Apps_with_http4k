package _05_slides

import org.http4k.core.HttpHandler

interface Filter : (HttpHandler) -> HttpHandler