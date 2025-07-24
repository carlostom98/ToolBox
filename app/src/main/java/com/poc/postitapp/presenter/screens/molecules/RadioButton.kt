package com.poc.postitapp.presenter.screens.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonCustomized(options: List<String>, onRatioButtonSelected: (String) -> Unit) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.selectableGroup()) {
        options.forEach { option ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .selectable(
                    selected = (option == selectedOption),
                    onClick = {
                        onOptionSelected(option)
                        onRatioButtonSelected(option)
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (option == selectedOption),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.secondary,
                        unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    ),
                    onClick = null
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }

}