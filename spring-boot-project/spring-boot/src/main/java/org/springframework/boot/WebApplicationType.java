/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot;

import org.springframework.util.ClassUtils;

/**
 * An enumeration of possible types of web application.
 * Web 应用类型
 *
 * @author Andy Wilkinson
 * @author Brian Clozel
 * @since 2.0.0
 */
public enum WebApplicationType {

	/**
	 * The application should not run as a web application and should not start an
	 * embedded web server.
	 *
	 * 非内嵌的 Web 应用
	 */
	NONE,

	/**
	 * The application should run as a servlet-based web application and should start an
	 * embedded servlet web server.
	 *
	 * 内嵌的 Servlet Web 应用，例如说，Spring MVC
	 */
	SERVLET,

	/**
	 * The application should run as a reactive web application and should start an
	 * embedded reactive web server.
	 *
	 * 内嵌的 Reactive Web 应用，例如说 Spring Webflux
	 */
	REACTIVE;

	private static final String[] SERVLET_INDICATOR_CLASSES = {
			// 这个是 java 中定义 Servlet 的类
			"javax.servlet.Servlet",
			// 这个应该是 Spring MVC 的 ApplicationContext
			"org.springframework.web.context.ConfigurableWebApplicationContext"
	};

	private static final String WEBMVC_INDICATOR_CLASS = "org.springframework.web.servlet.DispatcherServlet";

	private static final String WEBFLUX_INDICATOR_CLASS = "org.springframework.web.reactive.DispatcherHandler";

	private static final String JERSEY_INDICATOR_CLASS = "org.glassfish.jersey.servlet.ServletContainer";

	private static final String SERVLET_APPLICATION_CONTEXT_CLASS = "org.springframework.web.context.WebApplicationContext";

	private static final String REACTIVE_APPLICATION_CONTEXT_CLASS = "org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext";

	/** @return 从 classpath 上，判断 Web 应用类型 */
	static WebApplicationType deduceFromClasspath() {
		// 这里可以看出哪怕引入了 webflux，但是同时引入了 mvc，那么 spring boot 还是会将其当作 mvc 应用来看待
		if (
				// 存在 Spring Webflux 的类
				ClassUtils.isPresent(WEBFLUX_INDICATOR_CLASS, null)
						// Spring MVC 的类不存在
						&& !ClassUtils.isPresent(WEBMVC_INDICATOR_CLASS, null)
						&& !ClassUtils.isPresent(JERSEY_INDICATOR_CLASS, null)
		) {
			// 那么就是 WebApplicationType.REACTIVE 类型的应用
			return WebApplicationType.REACTIVE;
		}
		for (String className : SERVLET_INDICATOR_CLASSES) {
			if (!ClassUtils.isPresent(className, null)) { // 不存在 Servlet 的类
				return WebApplicationType.NONE;
			}
		}
		// 这只直接返回的原因是，引入 Spring MVC 时，如果是内嵌的 Web 应用，会引入 Servlet 的类
		return WebApplicationType.SERVLET;
	}

	static WebApplicationType deduceFromApplicationContext(Class<?> applicationContextClass) {
		if (isAssignable(SERVLET_APPLICATION_CONTEXT_CLASS, applicationContextClass)) {
			return WebApplicationType.SERVLET;
		}
		if (isAssignable(REACTIVE_APPLICATION_CONTEXT_CLASS, applicationContextClass)) {
			return WebApplicationType.REACTIVE;
		}
		return WebApplicationType.NONE;
	}

	private static boolean isAssignable(String target, Class<?> type) {
		try {
			return ClassUtils.resolveClassName(target, null).isAssignableFrom(type);
		} catch (Throwable ex) {
			return false;
		}
	}

}
