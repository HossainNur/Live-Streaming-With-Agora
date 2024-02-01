package com.example.livestreamingagora.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.livestreamingagora.R
import com.example.livestreamingagora.databinding.ActivityMainBinding
import com.example.livestreamingagora.models.LoginBody

class MainActivity : AppCompatActivity() {


    var userRole = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermission()

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar!!.title = "Live"
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA , android.Manifest.permission.RECORD_AUDIO), 22)
    }

    fun onSubmit(view: View){


        val userRadioButton = findViewById<View>(R.id.radio_group) as RadioGroup

        val checked = userRadioButton.checkedRadioButtonId
        val audienceButtonId = findViewById<View>(R.id.audience_rb) as RadioButton

        userRole = if (checked == audienceButtonId.id) 0 else 1

        val title = binding.titleEdtx.text.toString().trim()
        val description = binding.descriptionEdtx.text.toString().trim()

        if (title.isEmpty()) {
            binding.titleEdtx.setError("title required")
            binding.titleEdtx.requestFocus()
            return
        }
        if (description.isEmpty()) {
            binding.descriptionEdtx.setError("description required")
            binding.descriptionEdtx.requestFocus()
            return
        }

        if (!title.isNullOrEmpty() && !description.isNullOrEmpty()){
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("UserRole", userRole)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            startActivity(intent)
            binding.descriptionEdtx.setText("")
            binding.titleEdtx.setText("")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        finish()
        return super.onOptionsItemSelected(item)
    }


}