import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacter
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.CharactersViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.favorite.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterScreen(
    navController: NavHostController,
    viewModel: CharactersViewModel = koinViewModel(),
    favoriteViewModel: FavoriteViewModel
) {

    val characters = remember { mutableStateOf<List<CharacterResponseDto.Character>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.fetchAllCharacters()
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(characters.value) { character ->
                CharacterItem(character = character, onClick = {
                    navController.navigate("character_detail/${character.id}")
                }, onLongClick = {
                    val favoriteCharacter = FavoriteCharacter(
                        id = character.id,
                        name = character.name,
                        species = character.species,
                        image = character.image
                    )
                    favoriteViewModel.addCharacterToFavorites(favoriteCharacter)
                })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterItem(character: CharacterResponseDto.Character, onClick: () -> Unit, onLongClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(character.image)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = character.name, style = MaterialTheme.typography.titleLarge)
            Text(text = character.species, style = MaterialTheme.typography.titleMedium)
        }
    }
}
