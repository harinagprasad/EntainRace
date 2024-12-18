package com.app.nexttogo.presentation.ui.racesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nexttogo.domain.enums.RaceCategory
import com.app.nexttogo.domain.model.Race
import com.app.nexttogo.domain.usecase.FetchRacesUseCase
import com.app.nexttogo.utils.states.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceListViewModel @Inject constructor(
    private val fetchRacesUseCase: FetchRacesUseCase,
) : ViewModel() {

    private val _races = MutableStateFlow<Resource<List<Race>>>(Resource.Loading)
    val races = _races.asStateFlow()

    private val _selectedCategories = MutableStateFlow(emptyList<RaceCategory>())
    val selectedCategories = _selectedCategories.asStateFlow()

    init {
        fetchRace()
    }

    /**
     * function to fetch races
     */
    fun fetchRace() {
        viewModelScope.launch {
            fetchRacesUseCase().collect { result ->
                _races.tryEmit(result)
            }
        }
    }

    /**
     * function to filter the races
     */
    fun onFilter(raceCategory: RaceCategory, selected: Boolean) {
        val filters = mutableListOf<RaceCategory>()
        filters.addAll(selectedCategories.value)
        if (selected) {
            filters += raceCategory
        } else {
            filters -= raceCategory
        }
        _selectedCategories.tryEmit(filters)
    }
}
