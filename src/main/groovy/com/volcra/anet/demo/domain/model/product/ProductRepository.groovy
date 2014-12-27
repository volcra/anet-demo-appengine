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