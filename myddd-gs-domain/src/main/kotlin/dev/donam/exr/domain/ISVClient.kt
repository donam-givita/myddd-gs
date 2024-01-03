package dev.donam.exr.domain

import io.vertx.core.Future
import org.myddd.vertx.domain.BaseEntity
import org.myddd.vertx.ioc.InstanceFactory
import java.io.Serializable
import javax.persistence.*


enum class ISVClientType {
    APP,
    ISV
}

@Entity
@Table(
    name = "isv_client",
    indexes = [
        Index(name = "index_client_id", columnList = "client_id"),
        Index(name = "index_primary_id", columnList = "primary_id")
    ],
    uniqueConstraints = [UniqueConstraint(columnNames = ["client_id"])]
)
class ISVClient : BaseEntity() {
    @Column(name = "client_id", nullable = false, length = 36)
    lateinit var clientId: String

    companion object {
        private val repository by lazy { InstanceFactory.getInstance(ISVClientRepository::class.java) }

        fun create(id: String): ISVClient {
            return ISVClient().apply {
                clientId = id
            }
        }
    }

    suspend fun createISVClient(): Future<ISVClient> {
        return repository.save(this)
    }
}

@Entity
@Table(name = "oauth2_client")
class OAuth2Client: BaseEntity() {
    @Column(name = "client_id", nullable = false, length = 36)
    lateinit var clientId: String

    @Column(name = "client_name", nullable = false, length = 200)
    lateinit var clientName: String
}

class ISVAuthCode(val id: Long)

class ISVSuiteTicket(val id: Long)



class ISVClient2 {
    companion object {
        private val repository by lazy { InstanceFactory.getInstance(ISVClientRepository::class.java) }

        suspend fun createISVClient(): Future<ISVClient2> {
            return Future.future { ISVClient2() }
        }
    }
}