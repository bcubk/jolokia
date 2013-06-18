package org.jolokia.backend.dispatcher;

import java.io.IOException;

import javax.management.*;

import org.jolokia.backend.executor.NotChangedException;
import org.jolokia.request.JmxRequest;
import org.jolokia.service.JolokiaService;

/*
 * Copyright 2009-2013 Roland Huss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Interface for dispatching a request to a certain backend.
 *
 * @author roland
 * @since Nov 11, 2009
 */
public interface RequestHandler extends JolokiaService {
    /**
     * Dispatch a {@link JmxRequest} to a certain backend
     * and return the result of the JMX action.
     *
     * @param pJmxReq the request to dispatch
     * @return result object
     * @throws InstanceNotFoundException when a certain MBean could not be found
     * @throws AttributeNotFoundException in case an attributes couldn't be resolved
     * @throws ReflectionException
     * @throws MBeanException
     * @throws NotChangedException if the handled request's response hasnt changed (and the appropriate request parameter
     *         has been set).
     */
    Object handleRequest(JmxRequest pJmxReq)
            throws JMException, IOException, NotChangedException;

    /**
     * Check whether current dispatcher can handle the given request
     *
     * @param pJmxRequest request to check
     * @return true if this dispatcher can handle the request
     */
    boolean canHandle(JmxRequest pJmxRequest);

    /**
     * Whether a return value should be returned directly, ignoring any path.
     * E.g for the WriteHandler this is important to return the original value,
     * (using the path would return the new value)
     *
     * @param pJmxRequest request for getting the handler
     * @return true if the path within the request should be respected, false
     *         if the value should be directly returned
     */
    boolean useReturnValueWithPath(JmxRequest pJmxRequest);
}
