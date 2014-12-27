package com.volcra.anet.demo.domain.model.product

@groovy.transform.CompileStatic
interface ProductRepository {
    /**
     * <p>findOne.</p>
     *
     * @param id
     *         a ID object.
     * @return a Product object.
     */
    Product findOne(Long id)

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<Product> findAll()

    /**
     * <p>save.</p>
     *
     * @param entity
     *         a Product object.
     * @return a Product object.
     */
    Product save(Product entity)

    /**
     * <p>delete.</p>
     *
     * @param id
     *         a ID object.
     */
    void delete(Long id)
}