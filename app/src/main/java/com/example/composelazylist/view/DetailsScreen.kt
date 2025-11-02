package com.example.composelazylist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.composelazylist.R
import com.example.composelazylist.data.model.UserItem

@Composable
fun DetailsScreen(userItem: UserItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.default_space_16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space_4))
    ) {
        Text(
            text = userItem.title ?: "",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Text(
            text = userItem.body ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}