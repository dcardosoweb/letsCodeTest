package com.example.letscodetest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.letscodetest.LetsCodeTestApplication
import com.example.letscodetest.R
import com.example.letscodetest.databinding.ActivityLoginBinding
import com.example.letscodetest.databinding.ActivityMainBinding
import com.example.letscodetest.utils.SessionUser
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.buttonMovies.setOnClickListener {
            startActivity(Intent(this, TopRatedMoviesActivity::class.java))
        }

        binding.buttonMusic.setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }

        binding.buttonLogoff.setOnClickListener{
            firebaseAuth.signOut()
            SessionUser.userId=null
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}