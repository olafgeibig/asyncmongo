package com.tutaona.asyncmongo;

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.tutaona.asyncmongo.repository.UserRepository
import com.tutaona.asyncmongo.resource.UserResource

/**
 * User: olaf.geibig@tutaona.com
 * Created: 27.03.15 22:25
 */
public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserRepository).in(Scopes.SINGLETON)
        bind(UserResource).in(Scopes.SINGLETON)
    }
}