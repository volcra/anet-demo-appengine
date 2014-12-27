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
