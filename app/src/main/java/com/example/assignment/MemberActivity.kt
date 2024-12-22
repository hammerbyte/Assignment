package com.example.assignment

import DatabaseHelper
import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.databinding.ActivityMainBinding

class MemberActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        dbHelper = DatabaseHelper(this)

        val etMobile = findViewById<EditText>(R.id.et_membermobilenumber)
        val etName = findViewById<EditText>(R.id.et_membername)
        val rbSecretary = findViewById<RadioButton>(R.id.rb_secretary)
        val rbMale = findViewById<RadioButton>(R.id.rb_male)
        val rbMarried = findViewById<RadioButton>(R.id.rb_married)
        val etSubscriptionFee = findViewById<EditText>(R.id.et_subscriptionfee)
        val etDepositAmount = findViewById<EditText>(R.id.et_depositamount)
        val etLoanAmount = findViewById<EditText>(R.id.et_loanamount)
        val etDob = findViewById<EditText>(R.id.et_dateofbirth)
        val etDoj = findViewById<EditText>(R.id.et_dateofjoining)
        val etMarriageDate = findViewById<EditText>(R.id.et_dateofmarriage)
        val etCaste = findViewById<EditText>(R.id.et_caste)
        val etReligion = findViewById<EditText>(R.id.et_religion)
        val etCategory = findViewById<EditText>(R.id.et_category)
        val etAadhaar = findViewById<EditText>(R.id.et_aadhar)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            val mobile = etMobile.text.toString().trim()
            val name = etName.text.toString().trim()
            val role = if (rbSecretary.isChecked) "Secretary" else "Member"
            val gender = if (rbMale.isChecked) "Male" else "Female"
            val maritalStatus = if (rbMarried.isChecked) "Married" else "Unmarried"
            val subscriptionFee = etSubscriptionFee.text.toString().trim()
            val depositAmount = etDepositAmount.text.toString().trim()
            val loanAmount = etLoanAmount.text.toString().trim()
            val dob = etDob.text.toString().trim()
            val doj = etDoj.text.toString().trim()
            val marriageDate = etMarriageDate.text.toString().trim()
            val caste = etCaste.text.toString().trim()
            val religion = etReligion.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val aadhaar = etAadhaar.text.toString().trim()

            if (mobile.isEmpty() || name.isEmpty() || subscriptionFee.isEmpty()) {
                Toast.makeText(this, "Please fill mandatory fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_MOBILE, mobile)
                put(DatabaseHelper.COLUMN_NAME, name)
                put(DatabaseHelper.COLUMN_ROLE, role)
                put(DatabaseHelper.COLUMN_GENDER, gender)
                put(DatabaseHelper.COLUMN_MARITAL_STATUS, maritalStatus)
                put(DatabaseHelper.COLUMN_DEPOSIT, depositAmount.toDoubleOrNull())
                put(DatabaseHelper.COLUMN_LOAN, loanAmount.toDoubleOrNull())
                put(DatabaseHelper.COLUMN_DOB, dob)
                put(DatabaseHelper.COLUMN_DOJ, doj)
                put(DatabaseHelper.COLUMN_DOM, marriageDate)
                put(DatabaseHelper.COLUMN_CASTE, caste)
                put(DatabaseHelper.COLUMN_RELIGION, religion)
                put(DatabaseHelper.COLUMN_CATEGORY, category)
                put(DatabaseHelper.COLUMN_AADHAR, aadhaar)
            }

            val result = dbHelper.insertMember(values)
            if (result != -1L) {
                Toast.makeText(this, "Member Added Successfully", Toast.LENGTH_SHORT).show()
                finish() // Return to the main page
            } else {
                Toast.makeText(this, "Failed to Add Member", Toast.LENGTH_SHORT).show()
            }
        }
    }
}