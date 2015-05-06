package com.tutaona.asyncmongo.resource

import com.tutaona.asyncmongo.domain.User
import com.tutaona.asyncmongo.repository.UserRepository
import org.bson.types.ObjectId
import ratpack.exec.Promise

import static ratpack.groovy.test.handling.GroovyRequestFixture.handle
import spock.lang.Specification

/**
 * User: olaf.geibig@tutaona.com
 * Created: 04.05.15 22:21
 */
class UserResourceSpec extends Specification {

    def "render user"() {
        given:
        def user = new User(username:'bar', email:'foo@bar.org', created:new Date())
        def id = new ObjectId()

//        def userRepository = Mock(constructorArgs: [null, null], UserRepository)
//        userRepository.get(_) >> [then:{user}] as Promise
//        def userRepository// = [get:{new ExecControl()}] as UserRepository
//        def u = ExecHarness.yieldSingle { execControl ->
//            userRepository = [get:{execControl.blocking{user}}] as UserRepository
//        }

        def userRepository = [get:{[then:{Closure c-> println c}] as Promise}] as UserRepository

        when:
        def result = handle(new UserResource(userRepository)) {
            uri id.toString()
            method 'get'
            header 'Accept', 'application/json'
        }

        then:
        with(result) {
            rendered(User) != null
        }
    }

}
