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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.tvshowsapp.R


@Composable
fun SearchComponentWrapper(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val pearlColor = Color(parseColor("#F5F5F5"))

    Box(
        modifier = Modifier
            .background(color = pearlColor)
            .padding(horizontal = 16.dp)
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
            text = stringResource(id = R.string.search_title),
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
                placeholder = { Text(stringResource(id = R.string.search_placeholder)) },
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
                    unfocusedIndicatorColor = Color.Transparent, // Hide the indicator
                ),
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
                Text(text = stringResource(id = R.string.search_button))
            }
        }
    }
}

