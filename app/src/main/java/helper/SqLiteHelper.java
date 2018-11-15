package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


// this helper will Add/search/update/delete employees
//CRUD OPERATIONS
//sqlite- BUILD IN DATABASE IN ANDROID

public class SqLiteHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "EmpDb";             // name of the database
    private static int DB_VERSION = 1;                   // database version
    private static String EMPLOYEE_TABLE = "employee";   // employee table name



    //---------column names for employee table
    private static String NAME_COLUMN ="name";
    private static String LOCATION_COLUMN ="location";
    private static String DESIGNATION_COLUMN ="designation";

    // create a context variable

    Context context;

    // Constructor for creating the Database EmpDb
    public SqLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context; // assign the context from the Activity class to the global context
    }




    // the 2 methods below must be implemented (onCreate and onUpgrade)
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ EMPLOYEE_TABLE +"("+NAME_COLUMN+" TEXT(50),"+LOCATION_COLUMN+" TEXT(50),"+DESIGNATION_COLUMN+" TEXT(50));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // used when there has been change in Db structure
        // drop table on upgrade
        db.execSQL("DROP TABLE "+ EMPLOYEE_TABLE);
        onCreate(db); // recreate the dropped table

    }
    //---------------**** Create/ add a record****------------------------------
    public void insertEmployee(String name , String location, String designation){

        // Add an employee
        SQLiteDatabase database = this.getWritableDatabase();  // create an object of the current Db and Make it Writable so data can be added

        //========================== using NoSQL====================================
        // NoSQL is easier to use when adding record

        //step 1: set the values
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN,name);
        values.put(LOCATION_COLUMN, location);
        values.put(DESIGNATION_COLUMN, designation);

        // step 2: Insert the set values into a table
       long response = database.insert(EMPLOYEE_TABLE,null,values); // insert the values and return a long to be used to check if values were saved

       // step 3: check if record has been saved using the response returned by the insert method
        if(response==-1){
            //failed to save
            Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show();
        }
        else {
            // saved message
            Toast.makeText(context, "Successfully Saved Record No"+response, Toast.LENGTH_SHORT).show();
        }

        //========================== using SQL========================================
        //database.execSQL("INSERT INTO "+EMPLOYEE_TABLE+"("+NAME_COLUMN+","+LOCATION_COLUMN+","+DESIGNATION_COLUMN+") VALUES('Joseph Mwangi,197 Lenana Place, IT Officer');");


    }// end create/ add record

    //-------------------*****Read Employee****------------------------------------------
    public StringBuilder readEmployee(){


        StringBuilder empBuilder = new StringBuilder(); // string builder object that will hold all the records


        SQLiteDatabase database =this.getReadableDatabase(); // create an object of the current db and make it readable
        //==========================using SQL=================
        // SQL is easier when reading record

        //Create a cursor (stores rows returned either 0 rows or more
       Cursor cursor = database.rawQuery("SELECT * FROM "+EMPLOYEE_TABLE, null);

       // use the cursor to check records in the DB
        if(cursor.getCount()<1){
            // no record found
            Toast.makeText(context, "No Employee Found, press add button to create new employee", Toast.LENGTH_SHORT).show();
        }
        else {
            // get employee records in the db

            while (cursor.moveToNext()){ //while can move to next record
               empBuilder.append(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));
               empBuilder.append("  ");
                empBuilder.append(cursor.getString(cursor.getColumnIndex(LOCATION_COLUMN)));
                empBuilder.append("  ");
                empBuilder.append(cursor.getString(cursor.getColumnIndex(DESIGNATION_COLUMN)));
                empBuilder.append("\n\n");


            }

        }

    return empBuilder; // return StringBuilder with the Records
    }// end of readEmployee method








}// end of class
