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
class config {
  private static final byte configversion = 1;
  private static final String defaultconfigfile = "comparer.properties";
  private static final String defaulturl = "https://raw.githubusercontent.com/Arthurmeade12/comparer/main/";
  public String configfile;
  public String url;
  static class values {
    protected static boolean debug;
    protected static boolean cache_objects;
    protected static byte cache_size;
  }
  config() {
    configfile = defaultconfigfile;
    url = defaulturl;
  }
  config(String url, String file) {
    configfile = file;
    url = url;
  }
  protected boolean evalprops() {
    FileReader configreader;
    Properties p = new Properties();
    try {
      configreader = new FileReader(this.configfile);
      p.load(configreader);
    } catch (IOException a) {
      return false;
    }
    if (Byte.parseByte(p.getProperty("config_version")) < config.configversion) {
      msg.warn("Development environment detected. Local version is greater than the remote version.");
      msg.warn ("Forcing debug messages on.");
      config.values.debug = true;
    } else {
      if (Byte.parseByte(p.getProperty("config_version")) > config.configversion) {
        msg.warn("The application is out of date. Please update.");
      }
      config.values.debug = Boolean.parseBoolean(p.getProperty("debug"));
    }
    return true;
  }
  protected boolean createprops() throws MalformedURLException, IOException, FileNotFoundException {
    // returns success
    msg.warn("Downloading default config at " + this.configfile + " in your PWD.");
    // Java > 20 : //URL remote = new URI(config.values.url).toURL();
    // The above does NOT compile on java < 20
    // What's below compiles on all versions, but is deprecated on java > 20(who cares?)
    URL remote = new URL(this.url);
    ReadableByteChannel rbc = Channels.newChannel(remote.openStream());
    FileOutputStream output = new FileOutputStream(this.configfile);
    FileChannel channel = output.getChannel();
    channel.transferFrom(rbc, 0, Long.MAX_VALUE);
    msg.out("Download of default config complete. Please restart the application.");
    return true;
  }
}
