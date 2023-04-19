package de.goldkugel.etl.configuration;

/**
 * This class contains the basic methods for a configurable object.
 * 
 * Furthermore, this is the basic class for all components of an etl
 * process since every component should be configurable. It is
 * important than more than only one configuration file can be used if
 * necessary. Please, define the specific amount of configuration
 * files needed to use the component separately in the class which
 * implements this interface.
 * 
 * @author Peter Pallaoro
 *
 */
public interface Configurable {

  /**
   * With this method it is possible to add a configuration file to this
   * class. It checks if the configuration file exists. A non existing
   * configuration file can not be added and, additionally, if the
   * provided file is a directory, every file in that directory and
   * sub-directories will be added. Please note that in case a directory
   * is given there is no recursion limit, every file in the given
   * directory will be added. Therefore, all links to other folders will
   * be followed.
   * 
   * @param path: the absolute or relative path to the file or folder
   *        which should be added.
   * @return the return value should be zero if the configuration file
   *         was added successfully, if the return value is above zero,
   *         warnings have occurred, while if the return value is below
   *         zero, errors occurred. For every warning and/or error a
   *         specific value is returned. This should be noted while
   *         implementing this method.
   */
  public int addConfigurationFile(String path);

  /**
   * This method, similar to addConfigurationFile(String path), adds
   * configuration files to this class. If the configuration file is a
   * directory, it adds every file in this directory recursively, until
   * the maximum folder-depth has been reached. Afterwards the process
   * of adding new configuration files within directories will no longer
   * executed. It is recommended to define additionally a general
   * maximum depth to make sure that there will not be any recursion
   * problems.
   * 
   * @param path: the absolute or relative path to the file or directory
   *        which should be added.
   * @param maxdepth: the maximum folder-depth the configuration file
   *        adding process should reach.
   * @return the return value should be zero if the configuration file
   *         was added successfully, if the return value is above zero,
   *         warnings have occurred, while if the return value is below
   *         zero, errors occurred. For every warning and/or error a
   *         specific value is returned. This should be noted while
   *         implementing this method.
   */
  public int addConfigurationFile(String path, int maxdepth);

  /**
   * Counts the amount of files registered as configuration files.
   * 
   * @return the total amount of files registered as configuration
   *         files.
   */
  public int getFileCount();

  /**
   * To initialise the configuration of the component. Please provide a
   * list of paths of those configuration files which shall be used
   * before calling this method.
   * 
   * @return the return value should be zero if the configuration was
   *         successful, if the return value is above zero, warnings
   *         have occurred, while if the return value is below zero,
   *         errors occurred.
   */
  public int configurate();

  /**
   * When the whole content of a directory needs to be added some
   * warnings can occur and therefore, it should be possible to ignore
   * the warnings and proceed with the configuration. Nevertheless the
   * warning messages will be displayed and furthermore, if an error
   * occurs, the process of configuration will be halted.
   * 
   * @param ign: true, if the warnings should be ignored, false if not.
   */
  public void setIgnoringWarningsWhileAddingConfigurationFiles(boolean ign);

  /**
   * When the configuration process starts and multiple files need to be
   * processed ignoring warnings could be beneficial during this
   * process. This method sets if the warnings should be ignored during
   * the configuration process.
   * 
   * @param ign: true, if the warnings should be ignored, false if not.
   */
  public void setIgnoringWarningsWhileConfigurating(boolean ign);

  /**
   * To deinitialise the configuration of the component. This method
   * should be called as soon as the component is not longer used to
   * close every unnecessary connections, open files etc.
   * 
   * @return the return value should be zero if the deinitialisation was
   *         successful, if the return value is above zero, warnings
   *         have occurred, while if the return value is below zero,
   *         errors occurred. For every warning and/or error a specific
   *         value is returned. This should be noted while implementing
   *         this method.
   */
  public int deconfigurate();

}
