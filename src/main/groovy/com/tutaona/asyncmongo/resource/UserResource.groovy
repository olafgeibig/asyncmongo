package com.tutaona.asyncmongo.resource

import com.tutaona.asyncmongo.domain.User
import com.tutaona.asyncmongo.repository.UserRepository
import groovy.util.logging.Slf4j
import org.bson.types.ObjectId
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import javax.inject.Inject

import static ratpack.jackson.Jackson.json

@Slf4j
class UserResource implements Action<Chain> {

    private UserRepository userRepository

    @Inject
    UserResource(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    void execute(Chain chain) {

        Groovy.chain(chain) {

            handler(":id") {
                byMethod {
                    get {
                        ObjectId id = null
                        try {
                            id = new ObjectId(pathTokens.get("id"))
                        } catch (IllegalArgumentException ignored) {
                            log.warn "Id not found: ${pathTokens.get('id')}"
                            clientError(404)
                        }

                        if (id) {
                            userRepository.get(id).then { User user ->
                                println user
                                render json(user)
                            }
                        }
                    }
                }
            }

            handler {
                byMethod {
                    get {
                        userRepository.findAll().then { List<User> users ->
                            render json(users)
                        }
                    }

                    post {
                        def user = parse(User)
                        userRepository.save(user).then {
                            render json(user)
                        }
                    }
                }
            }

        }
    }

}