package org.grible.server;

import com.google.common.base.Objects;
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

@SuppressWarnings("all")
public class ServerRunner {
  private static String webRoot = ".";
  
  private static String war = "grible.war";
  
  private static int port = 8123;
  
  public static void main(final String[] args) throws Exception {
    ServerRunner.parseArgs(args);
    Server server = new Server(ServerRunner.port);
    WebAppContext webapp = new WebAppContext();
    File _file = new File(ServerRunner.webRoot);
    webapp.setTempDirectory(_file);
    webapp.setContextPath("/");
    webapp.setWar(ServerRunner.war);
    AnnotationConfiguration _annotationConfiguration = new AnnotationConfiguration();
    WebXmlConfiguration _webXmlConfiguration = new WebXmlConfiguration();
    WebInfConfiguration _webInfConfiguration = new WebInfConfiguration();
    PlusConfiguration _plusConfiguration = new PlusConfiguration();
    MetaInfConfiguration _metaInfConfiguration = new MetaInfConfiguration();
    FragmentConfiguration _fragmentConfiguration = new FragmentConfiguration();
    EnvConfiguration _envConfiguration = new EnvConfiguration();
    webapp.setConfigurations(new Configuration[] { _annotationConfiguration, _webXmlConfiguration, _webInfConfiguration, _plusConfiguration, _metaInfConfiguration, _fragmentConfiguration, _envConfiguration });
    server.setHandler(webapp);
    server.start();
    server.join();
  }
  
  private static void parseArgs(final String[] args) {
    for (final String arg : args) {
      {
        final String[] parts = arg.split("=");
        boolean _contains = arg.contains("=");
        if (_contains) {
          final String key = parts[0];
          final String value = parts[1];
          boolean _equals = Objects.equal(key, "--webroot");
          if (_equals) {
            ServerRunner.webRoot = value;
          } else {
            boolean _and = false;
            boolean _equals_1 = Objects.equal(key, "--httpPort");
            if (!_equals_1) {
              _and = false;
            } else {
              boolean _isNumeric = StringUtils.isNumeric(value);
              _and = _isNumeric;
            }
            if (_and) {
              int _parseInt = Integer.parseInt(value);
              ServerRunner.port = _parseInt;
            } else {
              boolean _equals_2 = Objects.equal(key, "--war");
              if (_equals_2) {
                ServerRunner.war = value;
              }
            }
          }
        }
      }
    }
  }
}
