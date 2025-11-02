package com.example.interviewapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.interviewapplication.R
import com.example.interviewapplication.data.network.ProgressBarState
import com.example.interviewapplication.data.network.Resource

@Composable
fun HomeScreen(mainViewModel: MainViewModel, onAction: (MainAction) -> Unit) {
    val listResponse = mainViewModel.userListResponse.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.default_space_16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space_8))
    ) {
        items(mainViewModel.userListToShow.value) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.default_space_16)))
                    .clickable(onClick = {
                        onAction.invoke(MainAction.OpenDetails(it))
                    })
                    .background(Color.Green.copy(alpha = .1f))
                    .padding(dimensionResource(R.dimen.default_space_16)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space_4))
            ) {
                Text(
                    text = it.title ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Text(
                    text = it.body ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }

    when (val data = listResponse.value) {
        is Resource.Success -> {
            data.data?.userList?.let {
                mainViewModel.userListToShow.value = it
            }
        }

        is Resource.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.failed_to_get_data),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }

        is Resource.Loading -> {
            if (data.progressBarState == ProgressBarState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}