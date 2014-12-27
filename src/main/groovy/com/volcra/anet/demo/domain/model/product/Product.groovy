package com.volcra.anet.demo.domain.model.product

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.googlecode.objectify.annotation.OnSave
import groovy.transform.ToString

@ToString
@Entity
@groovy.transform.CompileStatic
class Product implements Serializable {
    @Id
    Long id
    String description
    Double price
    Long quantity
    Date lastModified

    @OnSave
    void onSave() {
        lastModified = new Date()
    }
}
