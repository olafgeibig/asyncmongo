package com.tutaona.asyncmongo.repository

import groovy.transform.CompileStatic
import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import ratpack.exec.ExecControl
import rx.Observable
import static ratpack.rx.RxRatpack.observe

import javax.inject.Inject

@CompileStatic
abstract class RepositoryRx<T> {

    protected final ExecControl execControl
    protected final Datastore datastore
    protected final Class<T> entityClass

    @Inject
    RepositoryRx(ExecControl execControl, Datastore datastore, Class<T> entityClass) {
        this.execControl = execControl
        this.datastore = datastore
        this.entityClass = entityClass
    }

    RepositoryRx(){}

    Observable<List<T>> findAll() {
        observe execControl.blocking {
            return datastore.find(entityClass).asList()
        }
    }

    Observable<T> get(ObjectId id) {
        observe execControl.blocking {
            return datastore.get(entityClass, id)
        }
    }

    Observable<Void> save(T entity) {
        observe execControl.blocking {
            datastore.save(entity)
            //return null
        }
    }

}