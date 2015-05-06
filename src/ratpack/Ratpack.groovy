import com.mongodb.MongoClientURI
import com.tutaona.asyncmongo.MongoModule
import com.tutaona.asyncmongo.UserModule
import com.tutaona.asyncmongo.resource.UserResource
import ratpack.jackson.JacksonModule

import static ratpack.groovy.Groovy.ratpack

ratpack {

    bindings {

        add MongoModule, { config ->
            config.connectionString = new MongoClientURI("mongodb://localhost:27017/")
            config.dbName = "asyncmongo"
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
