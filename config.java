package me.arthurmeade12.comparer;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;
public class config {
  public static class values {
    public static boolean debug = false;
    private static final byte configversion = 1;
    private static final String configfile = "comparer.properties";
    private static final String url = "https://raw.githubusercontent.com/Arthurmeade12/comparer/main/" + configfile;
  }
  static void evalprops(){
    FileReader configreader;
    Properties p = new Properties();
    try {
      configreader = new FileReader(config.values.configfile);
      p.load(configreader);
    } catch (IOException a) {
      config.createprops();
      System.exit(0);
    }
    if (Byte.parseByte(p.getProperty("config_version")) < config.values.configversion) {
      msg.warn("Development environment detected. Local version is greater than the remote version.");
      msg.warn ("Forcing debug messages on.");
      config.values.debug = true;
    } else {
      if (Byte.parseByte(p.getProperty("config_version")) > config.values.configversion) {
        msg.warn("The application is out of date. Please update.");
      }
      config.values.debug = Boolean.parseBoolean(p.getProperty("debug"));
    }
  }
  static boolean createprops() {
    // returns success
    msg.warn("Downloading default config at " + config.values.configfile + " in your PWD.");
    try {
      // Java > 20 : //URL remote = new URI(config.values.url).toURL();
      // The above does NOT compile on java < 20
      // What's below compiles on all versions, but is deprecated on java > 20 (who cares?)
      URL remote = new URL(config.values.url);
      ReadableByteChannel rbc = Channels.newChannel(remote.openStream());
      FileOutputStream output = new FileOutputStream(config.values.configfile);
      FileChannel channel = output.getChannel();
      channel.transferFrom(rbc, 0, Long.MAX_VALUE);
    }
    catch (MalformedURLException a) {
      a.printStackTrace();
      msg.die("Invalid URL.");
      return false;
    }
    catch (IOException b) {
      b.printStackTrace();
      msg.die("Could not contact the remote server at " + config.values.url + ".");
      return false;
    }
    msg.out("Download of default config complete. Please restart the application.");
    return true;
  }
}
