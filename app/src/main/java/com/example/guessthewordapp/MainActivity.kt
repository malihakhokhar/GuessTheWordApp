package com.example.guessthewordapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.guessthewordapp.databinding.ActivityMainBinding
import android.view.View
import java.util.Arrays
import java.util.Collections
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    internal var words =
        arrayOf(// Days of the Week
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",

            // Easy Words
            "Apple",
            "Book",
            "Table",
            "Chair",
            "Phone",
            "Bread",
            "Water",
            "Light",
            "Mouse",
            "Cloud",

            // Medium Words
            "Laptop",
            "Window",
            "Bottle",
            "Pencil",
            "Orange",
            "Camera",
            "Planet",
            "Flower",
            "Rocket",
            "Market",

            // Hard Words
            "Computer",
            "Mountain",
            "Adventure",
            "Elephant",
            "Beautiful",
            "Chocolate",
            "Education",
            "Hospital",
            "Triangle",
            "Festival",

            // Tech / Fun Words
            "Android",
            "Kotlin",
            "Firebase",
            "Internet",
            "Galaxy",
            "Library",
            "Rainbow",
            "Diamond",
            "Sunflower",
            "Universe"
        )
    private lateinit var word: String
    private lateinit var random: Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        // Initialize random variable
        random = Random

        // Actual logic
        word = words[random.nextInt(words.size)]
        mainBinding.tvQuestionContainer.text = mixWord(word)

        // Set listener for the check button
        mainBinding.buttonCheck.setOnClickListener {
            if (mainBinding.etUserInput.text.toString().equals(word, ignoreCase = true)) {
                val dialog = Dialog(this@MainActivity)
                dialog.setContentView(R.layout.correct_dialog)
                val btn = dialog.findViewById<View>(R.id.btnConfirmDialog)
                dialog.show()

                btn.setOnClickListener {
                    dialog.dismiss()
                }

            } else {
                Toast.makeText(
                    this@MainActivity,
                    "You failed!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // ✅ Set listener for the next button
        mainBinding.buttonNext.setOnClickListener {
            // Generate a new random word and mix it
            word = words[random.nextInt(words.size)]
            mainBinding.tvQuestionContainer.text = mixWord(word)

            // Clear user input
            mainBinding.etUserInput.setText("")

        }

        // ✅ Set listener for the show button
        mainBinding.buttonShow.setOnClickListener {
            // Make answer views visible
            mainBinding.tvRightAnswer.visibility = View.VISIBLE
            mainBinding.tvCorrectAnswer.visibility = View.VISIBLE

            // Set the right answer
            mainBinding.tvRightAnswer.text = word
        }
    }


    fun mixWord(word: String): String {
        // Convert word into a list of characters
        val word = Arrays.asList<String>(*word.split("".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
        Collections.shuffle(word)
        var mixed = ""
        for (i in word) {
            mixed += i
        }
        return mixed
    }
}