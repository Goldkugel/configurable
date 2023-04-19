package de.goldkugel.etl.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import de.goldkugel.etl.global.Messages;
import de.goldkugel.etl.global.Values;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * This class implements a few basic methods of a configurable object.
 * 
 * The implemented methods are following: addConfigurationFile(String,
 * int), addConfigurationFile(String), getFileCount(),
 * setIgnoringWarningsWhileAddingConfigurationFiles(boolean), and
 * setIgnoringWarningsWhileConfigurating(boolean). Therefore, the
 * methods which are left to implement are: configurate() and
 * deconfigurate().
 * 
 * Please note the following implementation rules:
 * 
 * Warning or / and error messages are written in Messages.java and
 * can therefore, be reused. Values (Boolean, Integer, Double, and
 * Float values) are stored in Values.java but in this case for
 * security reasons. Please follow the design rules mentioned in the
 * classes. In general return values of methods should be zero if
 * everything went well, greater than zero if some warning occurred,
 * lower than zero if some errors occurred. Exceptions are methods
 * which are designed to return a specific value like for example
 * counts and lengths etc.
 * 
 * @author Peter Pallaoro
 *
 */
@Log4j2
public abstract class ConfigurableObject implements Configurable {

  /*
   * Contains all paths to the configuration files. Every element in
   * this list is a path to a file and should be unique.
   */
  protected final List<String> configFiles = new ArrayList<String>();

  /**
   * true if the object has been configured, false if not.
   */
  @Getter
  protected boolean isConfigured;

  /**
   * true, if warnings should be ignored during the process of adding a
   * new configuration file, false if not. Default: false.
   */
  @Getter
  @Setter
  protected boolean ignoringWarningsWhileAddingConfigurationFiles;

  /**
   * true, if warnings should be ignored during the process of
   * configuration, false if not. Default: false.
   */
  @Getter
  @Setter
  protected boolean ignoringWarningsWhileConfigurating;

  /**
   * Implementation of the "addConfigurationFile" method. Please have a
   * look in the class "Configurable" for more information about this
   * method. Please add either only relative or absolute links but not
   * both, since the method should not compare absolute and relative
   * paths. If the same file will be given twice either errors or / and
   * warnings will occur, or the configuration will be overridden.
   * 
   * @param path: the absolute or relative path to the file which should
   *        be added.
   * @param maxdepth: the maximum depth the configuration file adding
   *        process should reach.
   * @return zero if the configuration file was added successfully, if
   *         the return value is above zero, warnings have occurred,
   *         while if the return value is below zero, errors occurred.
   *         For every warning and/or error a specific value is returned
   *         to be able to handle these cases.
   */
  @Override
  public int addConfigurationFile(@NonNull String path, int maxdepth) {
    int ret = Values.DECIMAL_ZERO;

    File f = new File(path);

    // Checking if the file exists.
    if (f.exists()) {

      // If the file is a directory, every file in the directory needs to be
      // added
      if (f.isDirectory()) {

        // Printing information about the new found configuration file.
        log.info(Messages.CONFIG_DIRECTORY_FOUND
            .replace(Messages.FILE_SPACEHOLDER, path));

        // If maxdepth is reached, no more directories will be opened. Since
        // this method
        // works with recursion such a limit is needed.
        if (maxdepth >= Values.DECIMAL_ZERO) {
          String[] childs = f.list();

          for (String child : childs) {
            if (ret == Values.DECIMAL_ZERO || (ret >= Values.DECIMAL_ZERO
                && this.isIgnoringWarningsWhileAddingConfigurationFiles())) {
              ret = this.addConfigurationFile(child,
                  maxdepth - Values.DECIMAL_ONE);
            }
          }
        } else {
          log.info(Messages.CONFIG_MAX_DEPTH_REACHED
              .replace(Messages.FILE_SPACEHOLDER, path));
        }

      } else {

        // The configuration file can only be added if it has not been added
        // yet.
        if (this.configFiles.contains(path)) {
          log.info(Messages.CONFIG_FILE_ALREADY_ADDED
              .replace(Messages.FILE_SPACEHOLDER, path));
          ret = Values.DECIMAL_ONE;
        } else {
          this.configFiles.add(path);
          log.info(Messages.CONFIG_ADD_FILE_INFO
              .replace(Messages.CLASS_SPACEHOLDER, this.getClass().getName())
              .replace(Messages.FILE_SPACEHOLDER, path)
              .replace(Messages.HASH_SPACEHOLDER,
                  ((Integer) this.hashCode()).toString())
              .replace(Messages.COUNT_SPACEHOLDER,
                  ((Integer) this.getFileCount()).toString()));

        }
      }
    } else {
      log.info(Messages.CONFIG_FILE_NOT_FOUND.replace(Messages.FILE_SPACEHOLDER,
          path));
      ret = Values.DECIMAL_MINUS_ONE;
    }

    if (ret >= Values.DECIMAL_ZERO) {
      this.isConfigured = Values.BOOLEAN_TRUE;
    }

    return ret;
  }

  /**
   * Implementation of the "addConfigurationFile" method. Please have a
   * look in the class "Configurable" for more information about this
   * method. The used folder-depth is defined in Values.java as
   * CONFIG_MAX_DEPTH.
   * 
   * @param path: the absolute or relative path to the file which should
   *        be added.
   * @return the same return value as addConfigurationFile(String, int).
   */
  @Override
  public int addConfigurationFile(@NonNull String path) {
    return this.addConfigurationFile(path, Values.CONFIG_MAX_DEPTH);
  }

  /**
   * Implementation of the "addConfigurationFile" method. Please have a
   * look in the class "Configurable" for more information about this
   * method.
   * 
   * @return the total amount of files registered as configuration
   *         files.
   */
  @Override
  public int getFileCount() {
    return this.configFiles.size();
  }
}
