/*
 * Copyright 2014 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
