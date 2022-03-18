package com.jsonplaceholder.typicode.utils

import io.restassured.RestAssured
import io.restassured.RestAssured.requestSpecification
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.LogConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTestObject {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Value("\${environment}")
    var environment: String? = null

    @Value("\${baseUrl}")
    var baseUrl: String? = null

    @Value("\${name}")
    var Name: String? = null

    @BeforeAll
    fun setup(){
        logger.info("## Starting the Test ##")
        val logConfig = LogConfig.logConfig()
            .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
        val config = RestAssuredConfig.config().logConfig(logConfig)

        requestSpecification = RequestSpecBuilder()
            .setBaseUri(baseUrl)
            //.setBasePath("users")
            .setContentType(ContentType.JSON)
            .setRelaxedHTTPSValidation()
            .setConfig(config)
            .build()
    }

    @AfterAll
    open fun tearDown(){
        logger.info("## Ending the Test ##")
        RestAssured.reset()
    }

}