/* Generated By:JavaCC: Do not edit this line. ParserConstants.java */
package librecat.org.catmandu.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 6;
  /** RegularExpression Id. */
  int DO = 7;
  /** RegularExpression Id. */
  int DOSET = 8;
  /** RegularExpression Id. */
  int END = 9;
  /** RegularExpression Id. */
  int ELSE = 10;
  /** RegularExpression Id. */
  int IF = 11;
  /** RegularExpression Id. */
  int UNLESS = 12;
  /** RegularExpression Id. */
  int LPAREN = 13;
  /** RegularExpression Id. */
  int RPAREN = 14;
  /** RegularExpression Id. */
  int STRING = 15;
  /** RegularExpression Id. */
  int QUOTED = 16;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "<SINGLE_LINE_COMMENT>",
    "\"do\"",
    "\"doset\"",
    "\"end\"",
    "\"else\"",
    "\"if\"",
    "\"unless\"",
    "\"(\"",
    "\")\"",
    "<STRING>",
    "<QUOTED>",
  };

}
