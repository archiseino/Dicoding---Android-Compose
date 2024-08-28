package com.example.jetheroes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetheroes.data.Hero
import com.example.jetheroes.data.HeroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JetHeroesViewModel(private val repository: HeroRepository) : ViewModel() {

    private var _groupedHeroes = MutableStateFlow(repository.getHeroes()
        .sortedBy { it.name }.groupBy { it.name[0] })
    val groupedHeroes: StateFlow<Map<Char, List<Hero>>> get() = _groupedHeroes

    private var _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchHeroes(query: String) {
        _query.value = query
        _groupedHeroes.value = repository.searchHeroes(query)
            .sortedBy { it.name }.groupBy { it.name[0] }

    }

}

class ViewModelFactory(private val repository: HeroRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetHeroesViewModel::class.java)) {
            return JetHeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
