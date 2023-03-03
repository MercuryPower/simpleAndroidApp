package com.example.mobileproject

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var userData : MutableList<String> = mutableListOf();
    private var userName : String = "undefined";
    private var password : Any = "";
    private var age: Any = 0;
    private lateinit var genderGroup: RadioGroup;
    private lateinit var selectedGenderButton: RadioButton;
    private var gender: String = "undefined";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickSend(view: View) {
        /* Определение имени */
        userName = findViewById<EditText>(R.id.userName).text.toString();
        if (userName.isEmpty()) {
            userName = "undefined";
            userData += userName;
        } else {
            userData.add(userName);
        };

        /* Определение возраста */
        if(age != ""){
            age = findViewById<EditText>(R.id.Age).toString();
            userData.add(age as String);
        }
        else {
            age = "undefined";
            userData.add(age.toString());
        }
        /* Определение выбранного пола (ламинат) */
        genderGroup = findViewById(R.id.radioGroup);
        val selectedGenderId: Int = genderGroup.checkedRadioButtonId; // Берём ID выбранного пола
        if (selectedGenderId > 0) {
            selectedGenderButton = findViewById(selectedGenderId); // Находим этот ID
            gender =
                selectedGenderButton.text.toString(); // Получаем и превращаем в строку
        } else {
            gender = "undefined";
        }
        userData.add(gender);

    }
    fun onClickReset(view: View){
        userName = findViewById<EditText>(R.id.userName).setText("").toString();
        password = findViewById<EditText>(R.id.Password).setText("");
        age = findViewById<EditText>(R.id.Age).setText("");
        genderGroup = findViewById<RadioGroup>(R.id.radioGroup);
        genderGroup.clearCheck();
    }
//        var age: EditText = findViewById<EditText>(R.id.Age)
//        val choosedGender : String;
//        val gender = radioGroup.checkedRadioButtonId;
//        val radioButton : String = findViewById<RadioButton>(gender).text.toString();
//        println(userName);
//        println(age);
    }