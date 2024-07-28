package com.example.speechrecognitionapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speechrecognitionapp.R
import com.example.speechrecognitionapp.ui.shared.components.AppTopBar
import com.example.speechrecognitionapp.ui.shared.components.CardComponent
import com.example.speechrecognitionapp.ui.theme.SpeechRecognitionAppTheme
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Composable
fun HomeScreen(
    db: FirebaseFirestore,
    modifier: Modifier = Modifier,

    // viewModel: HomeViewModel = viewModel()
) {
    SpeechRecognitionAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppTopBar(
                    title = R.string.top_bar_title_summary,
                    onProfileClick = {/*TODO: open profile screen.*/ },
                    modifier = Modifier.fillMaxWidth()
                )
            },
        ) { innerPadding ->
            HomeContent(
                db,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp)
            )
        }
    }
}

@Composable
fun HomeContent(
    db: FirebaseFirestore,
    modifier: Modifier = Modifier
) {

    var text by remember { mutableStateOf("Hello") }
    val user = hashMapOf(
        "born" to 2004,
        "email" to "fly@gmail.com",
        "first" to "Gerald",
        "last" to "Lovelace",
        "password" to "666"
    )

    val addyRef = db.collection("medication").document("oxy")

    LazyColumn(modifier = modifier) {
        item {
            CardComponent(
                "Medications",
                "Yesterday",
                "Magnesium",
                {/*TODO: Open component screen.*/}
            )
        }

        item {
            Column {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true,
                    readOnly = true
                )
                Button(onClick = {
                    // Add a new document with a generated ID
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }) {
                    Text("Submit")
                }

                Button(onClick = {
                    db.collection("users")
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d(TAG, "${document.id} => ${document.data}")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents.", exception)
                        }
                }) {
                    Text("Read")
                }
            }

        }
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    // HomeContent()
}