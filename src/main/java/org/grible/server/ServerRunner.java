package org.grible.server;

import java.io.File;

import org.apache.commons.lang.StringUtils;
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
	private static String webRoot = ".";
	private static int port = 8123;

	public static void main(String[] args) throws Exception {
		parseArgs(args);

		Server server = new Server(port);

		WebAppContext webapp = new WebAppContext();
		webapp.setTempDirectory(new File(webRoot));
		webapp.setContextPath("/");
		webapp.setWar("grible.war");
		webapp.setConfigurations(new Configuration[] { new AnnotationConfiguration(), new WebXmlConfiguration(),
				new WebInfConfiguration(), new PlusConfiguration(), new MetaInfConfiguration(),
				new FragmentConfiguration(), new EnvConfiguration() });
		server.setHandler(webapp);
		server.start();
		server.join();
	}

	private static void parseArgs(String[] args) {
		for (String arg : args) {
			String[] parts = arg.split("=");
			if (parts.length > 1) {
				String key = parts[0];
				String value = parts[1];
				if (key.equals("--webroot")) {
					webRoot = value;
				} else if (key.equals("--httpPort") && StringUtils.isNumeric(value)) {
					port = Integer.parseInt(value);
				}
			}
		}
	}
}
