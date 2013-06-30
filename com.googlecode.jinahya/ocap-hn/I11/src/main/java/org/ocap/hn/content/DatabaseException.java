package org.ocap.hn.content;



/**
* Exception that is thrown when a database error occurs 
*/
public  class DatabaseException extends java.lang.Exception  {

//Constructors 

  public DatabaseException(
      java.lang.String sMessage,
      int iNumber
  ) {super();}

//Methods 

/**
* Returns the unique exception code related to the database. 
* @return the integer value with an exception code 
*/
  public   int getExceptionNumber(
  ) {return (0);}


//Fields 

  public static final  int FIELD_NAME_DOES_NOT_EXIST = 1;
  public static final  int FIELD_IS_EMPTY = 3;
  public static final  int FIELD_IS_WRONG_FORMAT = 4;
  public static final  int QUERY_IS_INVALID = 5;
  public static final  int INVALID_PARAMETER_SPECIFIED = 6;
  public static final  int UNABLE_TO_LOCATE_SERVICE = 7;
  public static final  int REMOTE_QUERY_IS_INVALID = 8;
  public static final  int GENERAL_ERROR = 9;

//Inner classes 


//Tail 

}

