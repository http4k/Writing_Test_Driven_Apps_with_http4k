package _11

import org.http4k.core.Filter
import java.util.concurrent.atomic.AtomicInteger

fun CallCounter(count: AtomicInteger): Filter = Filter { next ->
    {
        count.getAndIncrement()
        next(it)
    }
}