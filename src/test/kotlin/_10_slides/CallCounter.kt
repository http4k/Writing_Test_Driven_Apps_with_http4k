package _10_slides

import org.http4k.core.Filter
import java.util.concurrent.atomic.AtomicInteger

fun CallCounter(count: AtomicInteger): Filter = Filter { next ->
    {
        count.getAndIncrement()
        next(it)
    }
}