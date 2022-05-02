package com.example.letscodetest.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.letscodetest.LetsCodeTestApplication
import com.example.letscodetest.databinding.ActivityLoginBinding
import com.example.letscodetest.utils.SessionUser
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var successNewUser=false
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        successNewUser = intent.getBooleanExtra("NEW_USER_SUCCESS",false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewCadastroUsuario.setOnClickListener {
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }

        binding.buttonLogin.setOnClickListener {
            loadMode(true)
            if(validateForm()){
                loginUser()
            }else{
                loadMode(false)
            }
        }

        infoSuccessNewUser()
    }

   private fun infoSuccessNewUser(){
       if(successNewUser){
           ToastUtils.showLong("Usuário cadastrado com sucesso")
       }
   }

    private fun loginUser(){
        firebaseAuth.signInWithEmailAndPassword(binding.editTextLoginEmail.text.toString(),
            EncryptUtils.encryptSHA256ToString(binding.editTextLoginPassword.text.toString()).lowercase(
            Locale.ROOT)).addOnCompleteListener {
                loadMode(false)
                if(it.isSuccessful){
                    it.result.user?.uid?.let { itId ->
                        SessionUser.userId = itId
                    }

                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }else{
                    ToastUtils.showLong("Credenciais Invalidas")
                }
        }
    }

    private fun validateForm():Boolean{
        var valid=true
        resetErrors()

        if(binding.editTextLoginEmail.text.isNullOrEmpty()){
            valid = false
            binding.editTextLoginEmail.error= "Campo Obrigatorio"
        }

        if(binding.editTextLoginPassword.text.isNullOrEmpty()){
            valid = false
            binding.editTextLoginPassword.error= "Campo Obrigatorio"
        }

        return valid
    }

    private fun resetErrors(){
        binding.editTextLoginEmail.error=null
        binding.editTextLoginPassword.error=null
    }

    private fun loadMode(loading:Boolean){
        if(loading){
            binding.buttonLogin.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.buttonLogin.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}