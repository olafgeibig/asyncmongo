package com.tutaona.asyncmongo.resource

import com.tutaona.asyncmongo.domain.User
import com.tutaona.asyncmongo.repository.UserRepository
import org.bson.types.ObjectId
import ratpack.exec.Promise
import ratpack.rx.RxRatpack

import static ratpack.groovy.test.handling.GroovyRequestFixture.handle
import spock.lang.Specification

/**
 * User: olaf.geibig@tutaona.com
 * Created: 04.05.15 22:21
 */
class UserResourceSpec extends Specification {

    def setup() {
        RxRatpack.initialize()
    }

    def "render user"() {
        given:
        def user = new User(username:'bar', email:'foo@bar.org', created:new Date())
        def id = new ObjectId()
        rx.Observable<User> userObservable = rx.Observable.just(user)
        def userRepository = Mock(UserRepository)
        userRepository.get(_) >> userObservable

        when:
        def result = handle(new UserResource(userRepository)) {
            uri id.toString()
            method 'get'
            header 'Accept', 'application/json'
        }

        then:
        with(result) {
            rendered(User) == user
        }
    }

    def "render user fails"() {
        given:
        def user = new User(username:'bar', email:'foo@bar.org', created:new Date())
        rx.Observable<User> userObservable = rx.Observable.just(user)
        def userRepository = Mock(UserRepository)
        userRepository.get(_) >> userObservable

        when:
        def result = handle(new UserResource(userRepository)) {
            uri new ObjectId().toString()
            method 'get'
            header 'Accept', 'application/json'
        }

        then:
        with(result) {
            rendered(User) == user
        }
    }
}
