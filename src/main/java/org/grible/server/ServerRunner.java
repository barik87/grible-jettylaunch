package org.grible.server;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class ServerRunner {
	public static void main(String[] args) throws Exception {
		Server server = new Server(1508);

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar("grible.war");
		webapp.setConfigurations(new Configuration[] { new AnnotationConfiguration(), new WebXmlConfiguration(),
				new WebInfConfiguration(), new PlusConfiguration(), new MetaInfConfiguration(),
				new FragmentConfiguration(), new EnvConfiguration() });
		server.setHandler(webapp);
		server.start();
		server.join();
	}
}
