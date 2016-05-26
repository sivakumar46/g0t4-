/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.autoconfigure.web;

import java.util.Map;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.ClassUtils;

/**
 *
 * @author Stephane Nicoll
 */
public class RestClientTestExecutionListener extends AbstractTestExecutionListener {

	private static final String MOCK_REST_SERVICE_SERVER_CLASS =
			"org.springframework.test.web.client.MockRestServiceServer";

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		if (ClassUtils.isPresent(MOCK_REST_SERVICE_SERVER_CLASS, classLoader)) {
			Map<String, MockRestServiceServer> servers = testContext.getApplicationContext().
					getBeansOfType(MockRestServiceServer.class);
			for (MockRestServiceServer server : servers.values()) {
				server.reset();
			}
		}
	}

}
