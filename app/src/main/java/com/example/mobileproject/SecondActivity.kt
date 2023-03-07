package com.example.mobileproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.databinding.ActivitySecondActivityBinding
import kotlin.random.Random


class SecondActivity : AppCompatActivity() {
    private lateinit var second_binding: ActivitySecondActivityBinding
    private var randomColor = Random;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        second_binding = ActivitySecondActivityBinding.inflate(layoutInflater)
        setContentView(second_binding.root)
        second_binding.changeButton.setOnClickListener{changeBackground()}
        setFinalForm()
        second_binding.nextButton.setOnClickListener{toTheNextPage()}
    }

    private fun toTheNextPage() {
        startActivity(Intent(this, ThirdActivity::class.java))
    }

    private fun changeBackground() {
        var color: Int = Color.rgb(randomColor.nextInt(10,256), randomColor.nextInt(10,256), randomColor.nextInt(10,256))
        second_binding.mainLayout.setBackgroundColor(color)
    }

    private fun setFinalForm() {
        var info = ""
        for (i in MainActivity.userDataList){
            info += "\n" + i + "\n"

        }
        second_binding.formInfo.setText(info)
    }
}