package com.poc.postitapp.presenter.screens.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.persistence.data.entitiesdb.UrgencyLevel


@Composable
fun <T> DropDownMenuPicker(
    text: String,
    spacerDistance: Dp,
    options: List<T>,
    onItemClick: (T) -> Unit
) {
    Row(modifier = Modifier.wrapContentHeight(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.width(300.dp)) {
            Text(
                text = text,
                fontStyle = FontStyle.Italic,
                fontSize = 25.sp,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(
            modifier = Modifier
                .width(spacerDistance)
        )

        MyDropDownMenu(options = options) {
            onItemClick(it)
        }
    }
}

@Composable
fun <T> MyDropDownMenu(options: List<T>, onItemClick: (T) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf(options[0]) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {

        Button(
            onClick = { expanded.value = true }, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                disabledContainerColor = Color.LightGray
            )
        ) {
            Text(
                text = "Pick One", style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedOption.value = option
                    expanded.value = false
                    onItemClick(option)
                }, modifier = Modifier
                    .width(100.dp)
                    .height(60.dp), text = {
                    when (option) {
                        is UrgencyLevel -> {
                            Text(text = option.toString())
                        }

                        is Color -> Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(option)
                        )
                    }
                })
            }
        }
    }
}
