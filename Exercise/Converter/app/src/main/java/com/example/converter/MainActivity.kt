package com.example.converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.converter.ui.theme.ConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StatefulConverter()
                    ConverterApp()
                    TwoWayConverterApp()
                }
            }
        }
    }
}

@Composable
fun TwoWayConverterApp(modifier: Modifier = Modifier) {
    var celsius by remember {
        mutableStateOf("")
    }
    var fahrenheit by remember {
        mutableStateOf("")
    }

    Text(text = stringResource(id = R.string.two_way_converter))
    GeneralTemperatureInput(scale = Scale.CELSIUS, input = celsius, onValueChanged = {newInput ->
        val conversion = convertToFahrenheit(newInput)
        celsius = newInput
        fahrenheit = conversion
    } )

    GeneralTemperatureInput(scale = Scale.FAHRENHEIT, input = fahrenheit, onValueChanged = {newInput ->
        val conversion = convertToCelcius(newInput)
        fahrenheit = newInput
        celsius = conversion

    } )


}

@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            onValueChange = onValueChanged,
            label = {
                Text(
                    text = stringResource(
                        id = R.string.enter_temperature
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
    }
}

@Composable
fun ConverterApp(
    modifier: Modifier = Modifier,
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column(modifier) {
        StatelessConverter(
            input = input,
            output = output,
            onValueChanged = { newInput ->
                input = newInput
                output = convertToFahrenheit(newInput)
            }
        )
    }
}


@Composable
fun StatefulConverter(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column {
        Text(text = stringResource(id = R.string.stateful_converter))
        OutlinedTextField(
            value = input, onValueChange = { newInput ->
                input = newInput
                output = newInput
            },
            label = { Text(text = stringResource(id = R.string.enter_temperature)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(stringResource(R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun StatelessConverter(
    input: String,
    output: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = stringResource(id = R.string.stateless_converter))
        OutlinedTextField(
            value = input, onValueChange = onValueChanged,
            label = { Text(text = stringResource(id = R.string.enter_temperature)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
    Text(stringResource(R.string.temperature_fahrenheit, output))

}


private fun convertToFahrenheit(celcius: String): String {
    return celcius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()
}

private fun convertToCelcius(fahrenheit: String): String {
    return fahrenheit.toDoubleOrNull()?.let {
        (it - 32) * (5 / 9)
    }.toString()
}

enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}
