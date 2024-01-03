package dev.donam.exr.bootstrap

import io.vertx.core.Vertx
import org.myddd.vertx.string.RandomIDString
import org.myddd.vertx.string.RandomIDStringProvider

class GuiceModule(vertx: Vertx):AbstractWebModule(vertx = vertx) {

    override fun configure() {
        super.configure()
    }
}