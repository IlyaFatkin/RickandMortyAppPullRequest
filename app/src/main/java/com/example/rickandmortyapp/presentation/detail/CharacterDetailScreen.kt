package com.example.rickandmortyapp.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBackClick: () -> Unit,
    viewModel: CharacterDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Детали персонажа")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            when (val state = uiState) {

                is CharacterDetailUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is CharacterDetailUiState.Error -> {
                    Text(
                        text = "Ошибка: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                is CharacterDetailUiState.Success -> {

                    val character = state.character

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {

                        AsyncImage(
                            model = character.image,
                            contentDescription = character.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(300.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = "Статус: ${character.status}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = "Вид: ${character.species}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}