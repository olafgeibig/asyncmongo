package com.tutaona.asyncmongo.repository

import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.mongodb.morphia.Datastore
import ratpack.exec.ExecControl
import com.tutaona.asyncmongo.domain.User

/**
 * User: olaf.geibig@tutaona.com
 * Created: 27.03.15 21:21
 */
@CompileStatic
class UserRepository extends RepositoryRx<User> {

    @Inject
    UserRepository(ExecControl execControl, Datastore datastore) {
        super(execControl, datastore, User)
    }

    UserRepository(){}
}
