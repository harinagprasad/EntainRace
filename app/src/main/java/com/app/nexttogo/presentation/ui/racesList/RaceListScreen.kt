package com.app.nexttogo.presentation.ui.racesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.nexttogo.R
import com.app.nexttogo.domain.enums.RaceCategory
import com.app.nexttogo.domain.model.Race
import com.app.nexttogo.presentation.components.AppChip
import com.app.nexttogo.presentation.components.AppText
import com.app.nexttogo.presentation.components.AppText.Body
import com.app.nexttogo.presentation.components.AppText.CircularTextView
import com.app.nexttogo.presentation.components.AppText.SubTitle
import com.app.nexttogo.presentation.components.AppText.Title
import com.app.nexttogo.utils.ApiError
import com.app.nexttogo.utils.AppConstants.MILLISECONDS
import com.app.nexttogo.utils.extensions.toFormattedCountdown
import com.app.nexttogo.utils.extensions.toFormattedDate
import com.app.nexttogo.utils.states.Resource
import kotlinx.coroutines.delay

@Composable
fun RaceListScreen(viewModel: RaceListViewModel) {
    val races by viewModel.races.collectAsStateWithLifecycle()
    val selectedCategories by viewModel.selectedCategories.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderItem()
        when (val result = races) {
            is Resource.Loading -> {
                LoadingScreen(modifier = Modifier.weight(1f))
            }

            is Resource.Success -> {
                val filteredList by remember {
                    derivedStateOf {
                        result.data.filter { race ->
                            selectedCategories.isEmpty() || race.categoryId in selectedCategories.map { it.categoryId }
                        }
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    RaceListFilter(selectedCategories, onFilter = viewModel::onFilter)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                            .weight(1f)
                    ) {
                        if (filteredList.isEmpty()) {
                            item { EmptyRaceItem() }
                        } else {
                            item {
                                Title(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.races),
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            items(filteredList) {
                                RaceItem(it, refreshList = {
                                    viewModel.fetchRace()
                                })
                            }
                        }
                        item { Spacer(modifier = Modifier.height(48.dp)) }
                    }
                }
            }

            is Resource.Error -> {
                ErrorItem(modifier = Modifier.weight(1f), result.apiError, viewModel::fetchRace)
            }
        }
    }
}

@Composable
private fun HeaderItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.entain),
            modifier = Modifier.height(32.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Title(
            text = stringResource(R.string.next_to_go_racing),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.loading_your_races))
    }
}

@Composable
private fun ErrorItem(modifier: Modifier, apiError: ApiError, retry: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            text = stringResource(R.string.oops_something_went_wrong),
            color = MaterialTheme.colorScheme.tertiary
        )
        Body(
            text = stringResource(R.string.error, apiError.message),
            color = MaterialTheme.colorScheme.tertiary
        )
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { retry() },
        ) {
            AppText.Label(
                text = stringResource(R.string.retry),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun RaceListFilter(
    selectedCategories: List<RaceCategory>,
    onFilter: (RaceCategory, Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AppChip(
            text = stringResource(R.string.greyhound),
            isSelected = selectedCategories.contains(RaceCategory.GREYHOUND),
            onClick = { onFilter(RaceCategory.GREYHOUND, it) })
        AppChip(
            text = stringResource(R.string.harness),
            isSelected = selectedCategories.contains(RaceCategory.HARNESS),
            onClick = { onFilter(RaceCategory.HARNESS, it) })
        AppChip(
            text = stringResource(R.string.horse),
            isSelected = selectedCategories.contains(RaceCategory.HORSE),
            onClick = { onFilter(RaceCategory.HORSE, it) })
    }
}


@Composable
private fun EmptyRaceItem() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Title(
            text = stringResource(R.string.no_races_found),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun RaceItem(race: Race, refreshList: () -> Unit) {
    var countdownTime by remember(race.advertisedStart) {
        mutableLongStateOf(race.advertisedStart - (System.currentTimeMillis() / MILLISECONDS))
    }
    LaunchedEffect(countdownTime) {
        if (countdownTime > -59) {
            delay(MILLISECONDS)
            countdownTime -= 1L
        } else {
            refreshList()
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularTextView("R${race.raceNumber}")
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    SubTitle(
                        modifier = Modifier.fillMaxWidth(),
                        text = race.meetingName,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Body(
                        text = race.advertisedStart.toFormattedDate(),
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Body(
                        text = RaceCategory.entries.find {
                            it.categoryId == race.categoryId
                        }?.categoryName ?: "",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                SubTitle(
                    text = countdownTime.toFormattedCountdown(),
                    color = if (countdownTime > 0) Color.Green else Color.Red
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
