import com.mongodb.MongoClientURI
import com.tutaona.ninolo.MongoModule
import com.tutaona.ninolo.UserModule
import com.tutaona.ninolo.resource.UserResource
import ratpack.jackson.JacksonModule

import static ratpack.groovy.Groovy.ratpack

ratpack {

    bindings {

        add MongoModule, { config ->
            config.connectionString = new MongoClientURI("mongodb://localhost:27017/")
            config.dbName = "ninolo"
        }

        add JacksonModule, { config ->
            config.modules(new com.commercehub.jackson.datatype.mongo.MongoModule())
        }

        add UserModule

    }

    handlers {

        prefix("api/user") {
            handler chain(registry.get(UserResource))
        }

    }

}
