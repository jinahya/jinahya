package org.ocap.hn.content.navigation;

/**
* <p>DatabaseQuery class. This class represents a query of the metadata 
* database. Methods are provided so that complex AND, OR, NOT expressions can 
* be created.</p> <p>Note that this object is immutable; the 
* <code>and()</code>, <code>or()</code> and <code>negate()</code> methods do 
* not affect this object but return references to a new query. <p>For 
* example:</p> 
* <blockquote><pre> 
* DatabaseQuery qa = DatabaseQuery.newInstance("Genre", DatabaseQuery.EQUALS, "3.4.11"); 
* DatabaseQuery qb = DatabaseQuery.newInstance ("SpokenLanguage", DatabaseQuery.EQUALS,"fr-CA"); 
* DatabaseQuery qc = qb.and(qa.negate()); 
* </pre></blockquote> 
* <p>So the final statement has no effect on the state of <code>qa</code> or 
* <code>qb</code> objects, which still represent non-negated, root 
* predicates. 
* @author Alex Ashley 
* @author Alexander.Kobzhev 
* @author Andrew Hickman 
* @version 1.3 
*/
public abstract class DatabaseQuery extends ContentDatabaseFilter  {

//Constructors 

  public DatabaseQuery(
  ) {}

//Methods 

/**
* Make a new DatabaseQuery for a specific value in a specific field. This is 
* how the root predicates of a query is formed. For example: 
* <blockquote><pre> 
* DatabaseQuery q1 = DatabaseQuery.newInstance("Title", DatabaseQuery.CONTAINS, "Foxes"); 
* DatabaseQuery q2 = DatabaseQuery.newInstance("Genre", DatabaseQuery.CONTAINS, "3.4.11"); 
* DatabaseQuery the_query = q1.and(q2); 
* </pre></blockquote> 
* @param fieldName The name of the field to compare. This field must be the 
* name of known field ID, i.e. FieldID.getInstance().hasKey(fieldName) must 
* return true. 
* @param comparison The type of comparison to make (CONTAINS, EXISTS, 
* LESS_THAN, etc) 
* @param value The value to check. For numeric fields, the value must be a 
* string that contains a number. For date fields, the value must be a date in 
* an ISO 8601 compliant format (e.g. of the form "2001-01-05T14:30:00Z" or 
* "2001-01-05T15:30:00+01:00"). For fields that contain an item from a 
* classification scheme (e.g. the "Genre" field), the ID of the controlled 
* term must be used (e.g. "3.4.11"). For comparison==DatabaseQuery.EXISTS, 
* this parameter is not used (it is safe to pass null in this case). 
* @throws DatabaseException if the specified fieldName is unknown, if the 
* comparison is unknown or value is invalid. 
*/
  public static   org.ocap.hn.content.navigation.DatabaseQuery newInstance(
      java.lang.String fieldName,
      int comparison,
      java.lang.String value
  ) throws org.ocap.hn.content.DatabaseException{return (null);}

/**
* Create a new DatabaseQuery based upon the logical AND of the predicates 
* represented by this query and the argument query. 
* @param query The second predicate for the AND 
* @throws DatabaseException if the AND of these two queries is known to be 
* invalid 
*/
  public  abstract org.ocap.hn.content.navigation.DatabaseQuery and(
      org.ocap.hn.content.navigation.DatabaseQuery query
  ) throws org.ocap.hn.content.DatabaseException;

/**
* Create a new DatabaseQuery object based upon the logical AND of the 
* predicates represented by this query and the argument query. The resulting 
* predicate will only match within the scope of the specified node. For 
* example: 
* <blockquote><pre> 
* DatabaseQuery a = DatabaseQuery.newInstance("Title",DatabaseQuery.CONTAINS,"grave"); 
* DatabaseQuery b = DatabaseQuery.newInstance("TitleLanguage",DatabaseQuery.EQUALS,"en"); 
* DatabaseQuery query = a.and(b,"Title"); 
* </pre></blockquote> 
* will cause a search for a title that 
* contains the name "grave" and has an English language title, within the 
* same title node. Without the context node, a valid match would be found 
* for: <blockquote><pre> <tva:ShortTitle xml:lang="fr">Dilemme 
* Grave</tva:ShortTitle> <tva:ShortTitle xml:lang="en">Serious 
* Dilemma</tva:ShortTitle> </pre></blockquote> because there is a title with 
* lang="en" and a title which contains "grave". 
* @param query The second predicate for the AND 
* @param contextNode The node within which all of the AND must be satisfied. 
* This node must be the name of known field ID, (i.e. 
* FieldID.getInstance().hasKey(fieldName) must return true) and must be a 
* node that is at least the highest level element represented by the two 
* predicates (i.e. the context node is not a child node of either predicate). 
* @throws DatabaseException if the AND of these two queries is known to be 
* invalid, or the contextNode is invalid 
*/
  public  abstract org.ocap.hn.content.navigation.DatabaseQuery and(
      org.ocap.hn.content.navigation.DatabaseQuery query,
      java.lang.String contextNode
  ) throws org.ocap.hn.content.DatabaseException;

/**
* Create a new DatabaseQuery based upon the logical OR of the predicates 
* represented by this query and the argument query. 
* @param query The second predicate for the OR 
* @throws DatabaseException if the OR of these two queries is known to be 
* invalid 
*/
  public  abstract org.ocap.hn.content.navigation.DatabaseQuery or(
      org.ocap.hn.content.navigation.DatabaseQuery query
  ) throws org.ocap.hn.content.DatabaseException;

/**
* Create a new DatabaseQuery, which is the logical NOT of this query. 
* @return a new DatabaseQuery object that is the logical negative of this 
* object. 
*/
  public  abstract org.ocap.hn.content.navigation.DatabaseQuery negate(
  ) ;


//Fields 

/**
* Used to specify a test for equality. For numbers and dates, an exact match 
* is made. For strings, a case-insensitive comparison is made 
*/
  public static final  int EQUALS = 1;
/**
* Operator to specify greater than . For numbers the test is "a&gt;b". For 
* Date fields, the test is "a is after b". For strings, the comparison is 
* based on case-insensitive dictionary ordering (i.e. which value occurs 
* first when sorted in alphabetical order). E.g. "dog" &gt; "cat" == true, 
* "Chimp" &gt; "dog" == false 
*/
  public static final  int GREATER_THAN = 2;
/**
* Operator to specify less than . For numbers the test is "a&lt;b". For Date 
* fields, the test is "a is before b". For strings, the comparison is based 
* on case-insensitive dictionary ordering (i.e. which one occurs first when 
* sorted in alphabetical order). 
*/
  public static final  int LESS_THAN = 3;
/**
* Operator to specify greater than or equals. See rules for GREATER_THAN and 
* EQUALS for how non-numeric fields are compared. 
*/
  public static final  int GREATER_THAN_OR_EQUALS = 4;
/**
* Operator to specify less than or equals. See rules for LESS_THAN and EQUALS 
* for how non-numeric fields are compared. 
*/
  public static final  int LESS_THAN_OR_EQUALS = 5;
/**
* Operator to check for a String being contained within the field The 
* comparison is case insensitive, non whole word. 
*/
  public static final  int CONTAINS = 6;
/**
* Operator to check for in-equality. NOT_EQUALS follows the same equality 
* checking rules as EQUALS 
*/
  public static final  int NOT_EQUALS = 7;
/**
* Operator to check to see if a field exists 
*/
  public static final  int EXISTS = 8;

}

