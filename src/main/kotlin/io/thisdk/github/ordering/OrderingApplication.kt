package io.thisdk.github.ordering

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class OrderingApplication

fun main(args: Array<String>) {
    runApplication<OrderingApplication>(*args)
}
