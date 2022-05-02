package com.example.letscodetest.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.letscodetest.databinding.ActivityRegisterUserBinding
import com.example.letscodetest.models.UserModel
import com.example.letscodetest.viewmodels.RegisterUserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val viewModel: RegisterUserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            loadMode(true)
            if(validateForm()){
                register()
            }else{
                loadMode(false)
            }
        }
    }

    private fun validateForm():Boolean{
        resetErrors()
        var valid = true
        if(binding.editTextRegisterName.text.isNullOrEmpty()){
            valid = false
            binding.editTextRegisterName.error= "Campo Obrigatorio"
        }else{
            viewModel.name = binding.editTextRegisterName.text.toString()
        }

        if(binding.editTextRegisterPassword.text.isNullOrEmpty()){
            valid = false
            binding.editTextRegisterPassword.error= "Campo Obrigatorio"
        }else{
            viewModel.password = binding.editTextRegisterPassword.text.toString()
        }

        if(binding.editTextRegisterEmail.text.isNullOrEmpty()){
            valid = false
            binding.editTextRegisterEmail.error= "Campo Obrigatorio"
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.editTextRegisterEmail.text).matches()){
            valid = false
            binding.editTextRegisterEmail.error= "Email invalido"
        }else{
            viewModel.email = binding.editTextRegisterEmail.text.toString()
        }

        return valid
    }

    private fun resetErrors(){
        binding.editTextRegisterName.error=null
        binding.editTextRegisterEmail.error=null
        binding.editTextRegisterPassword.error=null
    }

    private fun loadMode(loading:Boolean){
        if(loading){
            binding.buttonRegister.visibility = View.GONE
            binding.progressBarRegisterUser.visibility = View.VISIBLE
        }else{
            binding.buttonRegister.visibility = View.VISIBLE
            binding.progressBarRegisterUser.visibility = View.GONE
        }
    }

    private fun register(){
        firebaseAuth.createUserWithEmailAndPassword(viewModel.email,
            EncryptUtils.encryptSHA256ToString(viewModel.password).lowercase(
                Locale.ROOT))
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var user = UserModel(viewModel.name, viewModel.email, viewModel.password)
                    FirebaseAuth.getInstance().currentUser?.let { it ->
                        FirebaseDatabase.getInstance().getReference("users")
                            .child(it.uid)
                            .setValue(user)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    redirectToLogin()
                                }else{
                                    ToastUtils.showLong("Ocorreu um erro ao cadastrar o usuário")
                                }
                            }
                    }
                } else {
                    ToastUtils.showLong("Ocorreu um erro ao cadastrar o usuário")
                }
                loadMode(false)
            }
    }

    fun redirectToLogin(){
        var intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("NEW_USER_SUCCESS",true)
        startActivity(intent)
    }
}