package com.example.tvshowsapp.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Color.parseColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun SearchComponentWrapper(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val pearlColor = Color(parseColor("#F5F5F5")) // Replace with your HEX color code

    Box(
        modifier = Modifier
            .background(color = pearlColor) // Set the background color
            .border(width = 2.dp, color = Color.LightGray, shape = MaterialTheme.shapes.medium) // Add a border
    ) {
        SearchComponent(query, onQueryChange, onSearchClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val buttonColor = Color(parseColor("#7393B3"))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Find Your Favorite TV Shows",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text("Search for tv shows") },
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
                    .weight(1f), // Take up available space in the row
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Hide the indicator
                    unfocusedIndicatorColor = Color.Transparent, // Hide the indicator
                    cursorColor = Color.Black // Set cursor color
                ),
//                trailingIcon = {
//                    IconButton(onClick = onSearchClick) {
//                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
//                    }
//                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSearchClick() },
            colors = ButtonDefaults.buttonColors(buttonColor),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Search Now")
            }
        }
    }
}

