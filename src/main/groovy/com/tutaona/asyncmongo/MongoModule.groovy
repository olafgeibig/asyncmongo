package com.tutaona.asyncmongo

import com.google.inject.Provides
import com.google.inject.Singleton
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import groovy.transform.CompileStatic
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia
import ratpack.guice.ConfigurableModule

/**
 * User: olaf.geibig@tutaona.com
 * Created: 27.03.15 21:15
 */
@CompileStatic
class MongoModule extends ConfigurableModule<Config> {

    static class Config {
        MongoClientURI connectionString
        String dbName
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    MongoClient mongoClient(Config config) {
        return new MongoClient(config.connectionString)
    }

    @Provides
    @Singleton
    Morphia morphia() {
        def morphia = new Morphia()
        morphia.mapPackageFromClass(com.tutaona.asyncmongo.domain.User)
        return morphia
    }

    @Provides
    @Singleton
    Datastore datastore(Config config, MongoClient mongoClient, Morphia morphia) {
        return morphia.createDatastore(mongoClient, config.dbName)
    }

}