package com.example.rickandmortyapiwithjetpackcompose.ui.module

import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.CharactersViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.detail.CharacterDetailViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.EpisodesViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.detail.EpisodeDetailViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.favorite.FavoriteViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.LocationViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.detail.LocationDetailViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule: Module = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}
