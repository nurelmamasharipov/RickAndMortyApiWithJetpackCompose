package com.example.rickandmortyapiwithjetpackcompose.ui.module

import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.CharactersViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.detail.CharacterDetailViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.LocationViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule: Module = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
}
