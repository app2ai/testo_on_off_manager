package com.example.motilal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.motilal.models.RepositoriesItem

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val d = intent.getParcelableExtra<RepositoriesItem>("DATA")
        println("RECEIVED DATA: $d")
    }
}