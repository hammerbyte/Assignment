package com.example.assignment

import DatabaseHelper
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var memberAdapter: MemberAdapter
    lateinit var btnAddMember : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.members_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        btnAddMember = findViewById(R.id.add_member_button)

        dbHelper = DatabaseHelper(this)

        btnAddMember.setOnClickListener {
            startActivity(Intent(this, MemberActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        loadMembers()
    }

    private fun loadMembers() {
        val cursor: Cursor = dbHelper.getAllMembers()
        val members = mutableListOf<Member>()

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME))
                val mobile = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MOBILE))
                val role = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROLE))
                val fee = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FEE))
                val deposit = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEPOSIT))
                val loan = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOAN))
                val gender = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENDER))
                val dob = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOB))
                val doj = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOJ))
                val maritial_status = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MARITAL_STATUS))
                val dom = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOM))
                val caste = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CASTE))
                val religion = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RELIGION))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY))
                val aadhar = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AADHAR))
                members.add(Member(name, role, mobile, fee, loan, deposit))
            } while (cursor.moveToNext())
        }
        cursor.close()

        memberAdapter = MemberAdapter(members)
        recyclerView.adapter = memberAdapter
    }
}
