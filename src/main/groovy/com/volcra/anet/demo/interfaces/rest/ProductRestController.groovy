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
package com.volcra.anet.demo.interfaces.rest

import com.volcra.anet.demo.domain.model.product.Product
import com.volcra.anet.demo.domain.model.product.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@groovy.transform.CompileStatic
@RequestMapping(value = '/api/product', produces = 'application/json')
class ProductRestController {
    ProductRepository repository

    @Autowired
    ProductRestController(ProductRepository productRepository) {
        repository = productRepository
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    Collection<Product> list() {
        repository.findAll()
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    Product create(@RequestBody Product p) {
        repository.save new Product(description: p.description, price: p.price, quantity: p.quantity)
    }

    @ResponseBody
    @RequestMapping(value = '/{id}', method = RequestMethod.GET)
    Product show(@PathVariable Long id) {
        repository.findOne id
    }

    @ResponseBody
    @RequestMapping(value = '/{id}', method = RequestMethod.PUT)
    Product update(@RequestBody Product p) {
        repository.save p
    }

    @ResponseBody
    @RequestMapping(value = '/{id}', method = RequestMethod.DELETE)
    Map delete(@PathVariable Long id) {
        repository.delete id

        [success: true]
    }
}
