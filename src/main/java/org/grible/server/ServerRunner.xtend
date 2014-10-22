package org.grible.server

import java.io.File;

import static extension org.apache.commons.lang.StringUtils.*;
import static extension java.lang.Integer.*;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class ServerRunner {
	static var webRoot = ".";
	static var war = "grible.war";
	static var port = 8123;

	def static main(String[] args) throws Exception {
		parseArgs(args);

		var server = new Server(port)
		var webapp = new WebAppContext()
		webapp.tempDirectory = new File(webRoot)
		webapp.contextPath = "/"
		webapp.war = war
		webapp.configurations = #[new AnnotationConfiguration(), new WebXmlConfiguration(), new WebInfConfiguration(),
			new PlusConfiguration(), new MetaInfConfiguration(), new FragmentConfiguration(), new EnvConfiguration()]
		server.handler = webapp
		server.start
		server.join
	}

	private def static parseArgs(String[] args) {
		for (arg : args) {
			val parts = arg.split("=")
			if (arg.contains('=')) {
				val key = parts.get(0)
				val value = parts.get(1)
				if (key == "--webroot") {
					webRoot = value;
				} else if (key == "--httpPort" && value.isNumeric) {
					port = value.parseInt
				} else if (key == "--war") {
					war = value;
				}
			}
		}
	}
}
