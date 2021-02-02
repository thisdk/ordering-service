package io.thisdk.github.ordering

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import java.util.*

@SpringBootApplication
@EnableCaching
class OrderingApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"))
    runApplication<OrderingApplication>(*args)
}
