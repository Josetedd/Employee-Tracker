package helper;

import android.content.ContentValues;
import android.content.Context;
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
            Toast.makeText(context, "Successfully "+response+" Saved", Toast.LENGTH_SHORT).show();
        }

        //========================== using SQL========================================
        //database.execSQL("INSERT INTO "+EMPLOYEE_TABLE+"("+NAME_COLUMN+","+LOCATION_COLUMN+","+DESIGNATION_COLUMN+") VALUES('Joseph Mwangi,197 Lenana Place, IT Officer');");


    }// end of method

    //-------------------*****Read Employee****------------------------------------------
    public void readEmployee(){
        SQLiteDatabase database =this.getReadableDatabase(); // create an object of the current db and make it readable

    }








}// end of class
