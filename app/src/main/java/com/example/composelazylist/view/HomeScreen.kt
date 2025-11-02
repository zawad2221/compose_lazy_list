package com.example.composelazylist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.error
import coil3.request.placeholder
import com.example.composelazylist.R

@Composable
fun HomeScreen(mainViewModel: MainViewModel, onAction: (MainAction) -> Unit) {
    val listResponse = mainViewModel.loadUserData.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.default_space_16)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space_8))
    ) {
        items(listResponse.itemCount) {
            val data = listResponse.get(it)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.default_space_16)))
                    .clickable(onClick = {
                        mainViewModel.selectedUserItem = data
                        data?.let { onAction.invoke(MainAction.OpenDetails) }
                    })
                    .background(Color.Green.copy(alpha = .1f))
                    .padding(dimensionResource(R.dimen.default_space_16)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space_4))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_space_8)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imageModel = ImageRequest.Builder(LocalContext.current)
                        .data("https://picsum.photos/400/400")
                        .error(R.drawable.person_24px)
                        .placeholder(R.drawable.person_24px)
                        .allowHardware(false)
                        .build()
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = .3f))
                            .padding(dimensionResource(R.dimen.default_space_2))
                    ) {
                        AsyncImage(
                            modifier = Modifier,
                            model = imageModel,
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = data?.title ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
                Text(
                    text = data?.body ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
        listResponse.apply {
            when {

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .size(dimensionResource(R.dimen.default_space_48)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                loadState.refresh is LoadState.Loading -> {
                    item {
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

    }
}