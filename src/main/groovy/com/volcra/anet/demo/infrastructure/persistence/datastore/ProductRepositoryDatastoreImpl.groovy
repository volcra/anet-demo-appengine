package com.volcra.anet.demo.infrastructure.persistence.datastore

import com.google.appengine.api.datastore.DatastoreService
import com.googlecode.objectify.ObjectifyService
import com.volcra.anet.demo.domain.model.product.Product
import com.volcra.anet.demo.domain.model.product.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository

import static com.googlecode.objectify.ObjectifyService.ofy

@Repository
@groovy.transform.CompileStatic
class ProductRepositoryDatastoreImpl implements ProductRepository {
    DatastoreService datastoreService

    @Autowired
    ProductRepositoryDatastoreImpl(DatastoreService datastoreService) {
        this.datastoreService = datastoreService
        ObjectifyService.register Product
    }

    @Override
    @Cacheable('products')
    Product findOne(Long id) {
        ofy().load().type(Product).id(id).now()
    }

    @Override
    Collection<Product> findAll() {
        ofy().load().type(Product).list() as ArrayList
    }

    @Override
    @CacheEvict(value = 'products', condition = '#entity.id != null', key = '#entity.id', beforeInvocation = true)
    Product save(Product entity) {
        findOne ofy().save().entity(entity).now().id
    }

    @Override
    @CacheEvict('products')
    void delete(Long id) {
        ofy().delete().type(Product).id(id).now()
    }
}
