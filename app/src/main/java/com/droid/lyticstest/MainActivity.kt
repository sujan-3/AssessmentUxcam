package com.droid.lyticstest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droid.lytics.data.LyticsEvent
import com.droid.lytics.tracker.Lytics
import com.droid.lyticstest.ui.theme.LyticsTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Lytics.initialize()

        enableEdgeToEdge()
        setContent {
            LyticsTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Analytics (Lytics) Test",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var showToast by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            Lytics.logEvent(
                LyticsEvent.Builder("signup_clicked")
                    .addProp("count", 1)
                    .build()
            )

            showToast = true

        }) {
            Text(text = "Click to signup")

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            Lytics.logEvent(
                LyticsEvent.Builder("login_clicked")
                    .addProp("login-type", "Gmail")
                    .build()
            )

            showToast = true
        }) {
            Text(text = "Login via Gmail")
        }
    }

    if (showToast) {
        Toast.makeText(LocalContext.current, "Clicked", Toast.LENGTH_SHORT).show()

        showToast = false
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LyticsTestTheme {
        Greeting("Android")
    }
}