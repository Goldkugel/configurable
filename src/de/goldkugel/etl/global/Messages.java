package de.goldkugel.etl.global;

import lombok.NonNull;

/**
 * Here every message regarding warnings, errors, and information is
 * stored. Moreover some helpful methods are also implemented for
 * displaying text.
 *
 * @author Peter Pallaoro
 *
 */
public final class Messages {

  /**
   * Placeholder in output for some hash value.
   */
  public static final String HASH_SPACEHOLDER = "[HASH]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CLASS_SPACEHOLDER = "[CLASS]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String FILE_SPACEHOLDER = "[FILE]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String COUNT_SPACEHOLDER = "[COUNT]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String NAME_SPACEHOLDER = "[NAME]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String INDEX_SPACEHOLDER = "[INDEX]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String TYPE_SPACEHOLDER = "[TYPE]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONTENT_SPACEHOLDER = "[CONTENT]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String GENERAL_INFO_ABOUT_OBJECT =
      "(Hash: ".concat(HASH_SPACEHOLDER).concat(", Class: ")
          .concat(CLASS_SPACEHOLDER).concat(")");
  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONFIG_INFO =
      "Configuration. ".concat(GENERAL_INFO_ABOUT_OBJECT);

  /**
   * Placeholder in output for some hash value.
   */
  public static final String DECONFIG_INFO =
      "Deconfiguration. ".concat(GENERAL_INFO_ABOUT_OBJECT);

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONFIG_ADD_FILE_INFO = "Configuration file "
      .concat(FILE_SPACEHOLDER)
      .concat(
          " added to the configuration file list. Current amount of configuration files: ")
      .concat(COUNT_SPACEHOLDER).concat(". ").concat(GENERAL_INFO_ABOUT_OBJECT);

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONFIG_FILE_NOT_FOUND = "Configuration file "
      .concat(FILE_SPACEHOLDER).concat(" could not be found.");

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONFIG_DIRECTORY_FOUND =
      "Configuration file ".concat(FILE_SPACEHOLDER)
          .concat(" is a directory. Adding all containing files.");

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONFIG_FILE_ALREADY_ADDED = "Configuration file "
      .concat(FILE_SPACEHOLDER).concat(" is already added.");

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONFIG_MAX_DEPTH_REACHED =
      "Maximum depth reached, no file in ".concat(FILE_SPACEHOLDER)
          .concat(" will be added.");

  /**
   * Text used in the toString method of the Atom. It is possible to add
   * the index, the type, and the content to the string by replacing the
   * spaceholders.
   */
  public static final String ATOM_TYPE_TO_STRING =
      "AtomType (Name = ".concat(NAME_SPACEHOLDER).concat(")");

  /**
   * Text used in the toString method of the Atom. It is possible to add
   * the index, the type, and the content to the string by replacing the
   * spaceholders.
   */
  public static final String ATOM_TO_STRING = "ATOM (Index = "
      .concat(INDEX_SPACEHOLDER).concat(", Type = ").concat(TYPE_SPACEHOLDER)
      .concat(", Content = ").concat(CONTENT_SPACEHOLDER).concat(")");

  /**
   * Adds the " quote at the beginning and the end of the given string.
   * 
   * @param s: the string which shall be quoted.
   * @return the quoted string.
   */
  public static String quote(@NonNull String s) {
    return "\"".concat(s).concat("\"");
  }
}
