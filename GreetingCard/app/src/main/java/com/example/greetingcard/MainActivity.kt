package com.example.greetingcard
//zay
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Access a Firestore instance from your Activity
        val db = Firebase.firestore
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("jules", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("julies", "Error adding document", e)
            }
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("jules", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("jules", "Error getting documents.", exception)
            }
        db.collection("userInfo")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("jules", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("jules", "Error getting documents.", exception)
            }

        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("shabba labba ding dong")
                }
            }
        }

    }
}



@Composable
fun Greeting(name: String) {
    Surface(color = Color.Blue){
        Text(text = "Hi, my name is $name!", modifier = Modifier.padding(22.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingCardTheme {
        Greeting("Julia")
    }
}
