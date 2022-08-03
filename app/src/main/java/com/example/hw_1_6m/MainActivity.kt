package com.example.hw_1_6m

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw_1_6m.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var launch: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        toSecondActivity()
        getTexts()
    }

    private fun toSecondActivity(){
        binding.btnSave.setOnClickListener {
            val text = binding.edText.text.toString()

            if (text.isNotBlank()) {

                intent(text)

            } else {

                Toast.makeText(
                    this,
                    "EditText не может быть пустым!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun intent(text: String){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("main", text)
        launch.launch(intent)
    }

    fun getTexts(){
        launch =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val intent: Intent? = it.data
                    val title = intent?.getStringExtra("SendMessage")
                    binding.edText.setText(title)
                }
            }
    }

}