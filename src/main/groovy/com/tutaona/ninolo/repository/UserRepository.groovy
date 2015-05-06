package com.tutaona.ninolo.repository

import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.mongodb.morphia.Datastore
import ratpack.exec.ExecControl
import com.tutaona.ninolo.domain.User

/**
 * User: olaf.geibig@tutaona.com
 * Created: 27.03.15 21:21
 */
@CompileStatic
class UserRepository extends Repository<User> {

    @Inject
    UserRepository(ExecControl execControl, Datastore datastore) {
        super(execControl, datastore, User)
    }

    UserRepository(){}
}
