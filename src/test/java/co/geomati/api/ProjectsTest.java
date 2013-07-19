package co.geomati.api;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import co.geomati.olakease.api.ProjectsResource;

public class ProjectsTest extends JerseyTest {
	// public ProjectsTest() {
	// uper(new WebAppDescriptor.Builder()
	// .contextParam("contextConfigLocation",
	// "classpath:/adminTestApplicationContext.xml")
	// .initParam("com.sun.jersey.config.property.packages",
	// "org.fao.unredd.api.resources;org.codehaus.jackson.jaxrs")
	// .contextPath("/admin")
	// .servletClass(ServletContainer.class)
	// .contextListenerClass(ContextLoaderListener.class)
	// .requestListenerClass(RequestContextListener.class)
	// .clientConfig(
	// new DefaultClientConfig(JacksonJsonProvider.class))
	// .build());
	//
	// }

	@Override
	protected Application configure() {
		return new ResourceConfig(ProjectsResource.class);
	}

	@Test
	public void testGetProjects() {
		String hello = target("projects").request().get(String.class);
		System.out.println(hello);
	}

}
