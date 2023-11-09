package com.example.team11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(DiaryFragment())
    }
    public fun loadFragment(fragment: Fragment, message : Int = 0) {
        val bundle = Bundle()
        bundle.putInt("message", message)
        fragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }
}