package com.example.mobileproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        var userDataList: MutableList<String> = mutableListOf()
    }
    private lateinit var choosedGender: String;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nameEventListener()
        passwordEventListener()
        ageEventListener()
        binding.submitButton.setOnClickListener{submitForm()}
        binding.resetButton.setOnClickListener{resetForm()}
    }

    private fun resetForm() {

        binding.userNameEditText.setText("")
        binding.passwordEditText.setText("")
        binding.ageEditText.setText("")
        binding.genderGroup.clearCheck()
    }


    private fun submitForm() {
        val validName = binding.userNameContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null
        val validAge = binding.ageContainer.helperText == null

        if(validName && validPassword && validAge){
            validForm()
        }
        else{
            invalidForm()
        }
    }

    private fun invalidForm() {
        var message : String = ""
        if(binding.userNameContainer.helperText != null){
            message += "\nВаше имя: " + binding.userNameContainer.helperText
        }
        if(binding.passwordContainer.helperText != null){
            message += "\nВаш пароль: " + binding.passwordContainer.helperText
        }
        if(binding.ageContainer.helperText != null){
            message += "\nВаш возраст: " + binding.ageContainer.helperText
        }

        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("Хорошо"){_,_ ->}
            .show()
    }

    private fun validForm() {
        userDataList.add(binding.userNameEditText.text.toString())

        userDataList.add(binding.ageEditText.text.toString())
        choosedGender = findViewById<RadioButton>(binding.genderGroup.checkedRadioButtonId).text.toString()
        if(choosedGender != null) {
            userDataList.add(choosedGender)
        }
        else{
            userDataList.add("null")
        };
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun nameEventListener() {
        binding.userNameEditText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                binding.userNameContainer.helperText = validName();
            }
        }
    }

    private fun validName(): String? {

        val userNameText = binding.userNameEditText.text.toString()
        if(userNameText.isEmpty()){
            return "Вы не заполнили это поле"
        }
        if(userNameText.length < 3){
            return "Длина имени должна состоять минимум из 3 символов"
        }

        return null
    }
    private fun passwordEventListener() {
        binding.passwordEditText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                binding.passwordContainer.helperText = validPassword();
            }
        }
    }

    private fun validPassword(): String? {

        val passwordText = binding.passwordEditText.text.toString()
        if(passwordText.isEmpty()){
            return "Вы не заполнили это поле"
        }
        if(passwordText.length < 8){
            return "Пароль должен состоять минимум из 8 символов"
        }

        return null
    }

    private fun ageEventListener() {
        binding.ageEditText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                binding.ageContainer.helperText = validAge();
            }
        }
    }

    private fun validAge(): String? {

        val ageText = binding.ageEditText.text.toString()
        if(ageText.isEmpty()){
            return "Вы не заполнили это поле"
        }

        return null
    }
}