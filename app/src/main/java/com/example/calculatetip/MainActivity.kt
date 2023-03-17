package com.example.calculatetip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatetip.ui.theme.CalculateTipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateTipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipScreen()
                }
            }
        }
    }
}

@Composable
fun TipScreen() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        var inputAmount by remember {
            mutableStateOf(
                "0"
            )
        }


        var tip by remember {
            mutableStateOf(
                "0.0"
            )
        }

        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = inputAmount,
            onValueChange = { costOfService ->
                inputAmount = costOfService
                tip = calculateTip(costOfService)
            },
            label = { Text(stringResource(id = R.string.cost_of_service))},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )



    }
}

fun calculateTip(newValue: String): String {
    /**
     * We will assume tip amount is 15% of the cost of service
     */
    val costOfService = newValue.toDoubleOrNull()
    costOfService?.let {
        return (it * 0.15).toString()
    }
    return "0.0"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculateTipTheme {
        TipScreen()
    }
}