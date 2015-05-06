package com.tutaona.ninolo.domain

import com.fasterxml.jackson.annotation.JsonFormat
import groovy.transform.Canonical
import groovy.transform.CompileStatic
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

/**
 * User: olaf.geibig@tutaona.com
 * Created: 17.11.14 21:41
 */
@Canonical
@Entity(noClassnameStored = true)
@CompileStatic
class User {

    @Id ObjectId id

    String username

    String email

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    Date created
}
