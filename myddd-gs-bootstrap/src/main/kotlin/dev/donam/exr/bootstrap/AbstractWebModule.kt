package dev.donam.exr.bootstrap

import com.google.inject.AbstractModule
import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import org.hibernate.reactive.mutiny.Mutiny
import org.myddd.vertx.file.FileDigest
import org.myddd.vertx.file.FileDigestProvider
import org.myddd.vertx.i18n.I18N
import org.myddd.vertx.i18n.provider.I18NVertxProvider
import org.myddd.vertx.querychannel.api.QueryChannel
import org.myddd.vertx.querychannel.hibernate.QueryChannelHibernate
import org.myddd.vertx.repository.api.EntityRepository
import org.myddd.vertx.repository.hibernate.EntityRepositoryHibernate
import org.myddd.vertx.string.RandomIDString
import org.myddd.vertx.string.RandomIDStringProvider
import org.myddd.vertx.config.Config
import org.myddd.vertx.id.IDGenerator
import org.myddd.vertx.id.SnowflakeDistributeId
import javax.persistence.Persistence

abstract class AbstractWebModule(private val vertx: Vertx) : AbstractModule() {


    override fun configure(){
        bind(Vertx::class.java).toInstance(vertx)
        bind(WebClient::class.java).toInstance(WebClient.create(vertx))
        bind(Mutiny.SessionFactory::class.java).toInstance(
            Persistence.createEntityManagerFactory("default",persistenceProps())
                .unwrap(Mutiny.SessionFactory::class.java))
        bind(EntityRepository::class.java).to(EntityRepositoryHibernate::class.java)
        bind(QueryChannel::class.java).to(QueryChannelHibernate::class.java)
        bind(IDGenerator::class.java).toInstance(SnowflakeDistributeId())

        bind(RandomIDString::class.java).to(RandomIDStringProvider::class.java)
        bind(FileDigest::class.java).to(FileDigestProvider::class.java)

        bind(I18N::class.java).to(I18NVertxProvider::class.java)
    }

    companion object {
        private const val JDBC_URL = "javax.persistence.jdbc.url"
        private const val JDBC_USER = "javax.persistence.jdbc.user"
        private const val JDBC_PASSWORD = "javax.persistence.jdbc.password"
        private const val POOL_SIZE = "hibernate.connection.pool_size"
        private const val DATABASE_ACTION = "javax.persistence.schema-generation.database.action"
        private const val IS_SHOW_SQL = "hibernate.show_sql"
        private const val IS_FORMAT_SQL = "hibernate.format_sql"
        private const val IS_HIGHLIGHT_SQL = "hibernate.highlight_sql"
    }

    private fun persistenceProps():Map<String,Any>{
        return mapOf(
            JDBC_URL to Config.getString(JDBC_URL),
            JDBC_USER to Config.getString(JDBC_USER),
            JDBC_PASSWORD to Config.getString(JDBC_PASSWORD),
            POOL_SIZE to Config.getInteger(POOL_SIZE),
            DATABASE_ACTION to Config.getString(DATABASE_ACTION),
            IS_SHOW_SQL to Config.getBoolean(IS_SHOW_SQL,false),
            IS_FORMAT_SQL to Config.getBoolean(IS_FORMAT_SQL,false),
            IS_HIGHLIGHT_SQL to Config.getBoolean(IS_HIGHLIGHT_SQL,false)
        )
    }

}