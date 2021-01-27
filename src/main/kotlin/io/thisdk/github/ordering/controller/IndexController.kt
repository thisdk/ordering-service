package io.thisdk.github.ordering.controller

import io.thisdk.github.ordering.bean.RestResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @RequestMapping("/index")
    fun index(): RestResponse<String> {
        return RestResponse("Hello Spring Boot, The Service Use Github Action Auto Deploy!")
    }

}