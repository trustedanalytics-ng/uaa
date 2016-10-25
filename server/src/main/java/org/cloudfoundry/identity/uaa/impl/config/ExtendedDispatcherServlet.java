/**
 * Copyright (c) 2016 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.identity.uaa.impl.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class ExtendedDispatcherServlet extends DispatcherServlet {

    @Override
    protected WebApplicationContext initWebApplicationContext()
    {
        try {
            return super.initWebApplicationContext();
        } catch (BeanCreationException e) {
            System.exit(1);
        }
        return null;
    }
}
