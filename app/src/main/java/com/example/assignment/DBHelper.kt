import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Members.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "members"
        const val COLUMN_NAME = "name"
        const val COLUMN_MOBILE = "mobile"
        const val COLUMN_ROLE = "role"
        const val COLUMN_FEE = "subscription_fee"
        const val COLUMN_DEPOSIT = "deposit_amount"
        const val COLUMN_LOAN = "loan_amount"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_DOB = "dob"
        const val COLUMN_DOJ = "doj"
        const val COLUMN_MARITAL_STATUS = "marital_status"
        const val COLUMN_DOM = "date_of_marriage"
        const val COLUMN_CASTE = "caste"
        const val COLUMN_RELIGION = "religion"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_AADHAR = "aadhaar"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_NAME TEXT,
                $COLUMN_MOBILE TEXT,
                $COLUMN_ROLE TEXT,
                $COLUMN_FEE TEXT,
                $COLUMN_DEPOSIT TEXT,
                $COLUMN_LOAN TEXT,
                $COLUMN_GENDER TEXT,
                $COLUMN_DOB TEXT,
                $COLUMN_DOJ TEXT,
                $COLUMN_MARITAL_STATUS TEXT,
                $COLUMN_DOM TEXT,
                $COLUMN_CASTE TEXT,
                $COLUMN_RELIGION TEXT,
                $COLUMN_CATEGORY TEXT,
                $COLUMN_AADHAR TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertMember(values: ContentValues): Long {
        val db = this.writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllMembers(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM members", null)
    }

}
