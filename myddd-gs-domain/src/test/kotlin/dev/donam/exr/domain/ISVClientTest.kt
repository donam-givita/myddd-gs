package dev.donam.exr.domain

import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.junit5.VertxTestContext
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID

class ISVClientTest: AbstractTest() {
    @Test
    fun testCreateISVClient(vertx: Vertx, testContext: VertxTestContext){
        GlobalScope.launch(vertx.dispatcher()) {
            try {
                val isvClient = ISVClient.create(UUID.randomUUID().toString())

                val created = isvClient.createISVClient().await()

                testContext.verify {
                    Assertions.assertNotNull(created)
                    Assertions.assertNotNull(created.clientId)
                    Assertions.assertTrue(created.getId() > 0)
                }

            } catch (e:Exception){
                testContext.failNow(e)
            }
            testContext.completeNow()
        }
    }
}

class MyClass {
    companion object {
        suspend fun create(): Future<MyClass> = Future.future { MyClass() }
    }
}