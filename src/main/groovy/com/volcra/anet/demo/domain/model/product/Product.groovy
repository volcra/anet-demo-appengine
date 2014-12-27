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
