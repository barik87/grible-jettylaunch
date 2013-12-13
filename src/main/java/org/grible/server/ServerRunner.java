package org.grible.server;

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
	private static int port = 8123;

	public static void main(String[] args) throws Exception {
		parseArgs(args);

		Server server = new Server(port);

		System.getProperties().setProperty("jetty.home",".");
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

	private static void parseArgs(String[] args) {
		if (args.length > 0) {
			if (StringUtils.isNumeric(args[0])) {
				port = Integer.parseInt(args[0]);
			} else {
				System.out.println("WARNING: 1st parameter ('" + args[0]
						+ "') is not numeric so ignored. Default port number " + port + " is used instead.");
			}
		}
	}
}
