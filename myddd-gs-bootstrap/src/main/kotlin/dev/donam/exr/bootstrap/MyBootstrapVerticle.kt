package dev.donam.exr.bootstrap

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import com.google.inject.Guice
import io.vertx.kotlin.coroutines.await
import org.myddd.vertx.ioc.InstanceFactory
import org.myddd.vertx.ioc.guice.GuiceInstanceProvider
import org.myddd.vertx.web.router.BootstrapVerticle


class MyBootstrapVerticle (port:Int = 8080) : BootstrapVerticle(port = port){

    override suspend fun initIOC(vertx: Vertx) {
        vertx.executeBlocking<Unit> {
            InstanceFactory.setInstanceProvider(GuiceInstanceProvider(Guice.createInjector(GuiceModule(vertx))))
            it.complete()
        }.await()
    }


    override fun routers(vertx: Vertx, router: Router): () -> Unit {
        return {
        }
    }
}