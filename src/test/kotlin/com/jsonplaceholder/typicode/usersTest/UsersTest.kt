package com.jsonplaceholder.typicode.usersTest

import com.jsonplaceholder.typicode.utils.BaseTestObject
import io.restassured.RestAssured.requestSpecification
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UsersTest: BaseTestObject() {

    private val logger = LoggerFactory.getLogger(javaClass)

    private var userId: String = "";

    @DisplayName("Get User Id test")
    @Test
    @Order(1)
    fun testAGetUsername() {
        try {
            logger.info("Username: "+Name)
            var data: Any? =
                Given {
                    spec(requestSpecification)
                } When {
                    log().all()
                    get("users?username=$Name")
                } Then {
                    statusCode(HttpStatus.SC_OK)
                } Extract {
                    path("id")
                }
            userId = data.toString().removeSurrounding("[", "]")
            logger.info("User Id " + userId)
        }
        catch (e: Exception){
            logger.error("Get Username test failed "+ e)
            throw e
        }

    }

    @DisplayName("Posts Email validation test")
    @Test
    @Order(2)
    fun testBGetPostsValidateEmail() {
        try {
            var posts: Any? =
                Given {
                    spec(requestSpecification)
                } When {
                    get("posts?userId="+userId)
                } Then {
                    statusCode(HttpStatus.SC_OK)
                } Extract {
                    path("id")
                }
            val resultPosts = posts.toString().replace("\\s".toRegex(), "").removeSurrounding("[", "]").split(",").map { it.toInt() }

            logger.info("Posts " + resultPosts)

            // Get Comments for each posts
            for (postItem in resultPosts) {
                var email: Any? =
                    Given {
                        spec(requestSpecification)
                    } When {
                        get("comments?postId=" + postItem)
                    } Then {
                        statusCode(HttpStatus.SC_OK)
                    } Extract {
                        path("email")
                    }
                logger.info("Post item "+postItem+". Comment email "+ email)
                val resultEmail = email.toString().replace("\\s".toRegex(), "").removeSurrounding("[", "]").split(",").map { it }

                // Validate email from the comments
                for (emailItem in resultEmail) {
                    logger.info("Email "+emailItem)
                    assertTrue(isEmailValid(emailItem),"Email is valid: " + emailItem)
                }
            }
        }
        catch (e: Exception){
            logger.error("Email validation test failed "+ e)
            throw e
        }
    }

    /*
    * Email validation Method
    * */
    private fun isEmailValid(email: String): Boolean {
        try {
            val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
            return EMAIL_REGEX.toRegex().matches(email);
        }
        catch (e: Exception){
            logger.error("isEmailValid methord failed "+ e)
            throw e
        }

    }

}