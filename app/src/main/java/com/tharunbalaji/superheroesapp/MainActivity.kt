package com.tharunbalaji.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tharunbalaji.superheroesapp.model.Hero
import com.tharunbalaji.superheroesapp.model.HeroesRepository.heroes
import com.tharunbalaji.superheroesapp.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SuperHeroesApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesApp() {
    Scaffold(topBar = {
        SuperHeroTopBar()
    }) { it ->
        LazyColumn(contentPadding = it, modifier = Modifier.fillMaxSize()) {
            items(heroes) { hero ->
                SuperHeroCard(
                    hero = hero,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge)
            }
        }
    )
}

@Composable
fun SuperHeroCard(
    hero: Hero,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SuperHeroInformation(
                nameRes = hero.nameRes,
                descriptionRes = hero.descriptionRes
            )
            Spacer(modifier = Modifier.weight(1f))
            SuperHeroImage(
                imageRes = hero.imageRes
            )
        }
    }
}

@Composable
fun SuperHeroInformation(
    @StringRes nameRes: Int,
    @StringRes descriptionRes: Int
) {
    Column(modifier = Modifier.height(72.dp)) {
        Text(
            text = stringResource(
                id = nameRes,
            ),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(
                id = descriptionRes,
            ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SuperHeroImage(
    @DrawableRes imageRes: Int
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .height(72.dp)
            .padding(start = 16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun SuperheroesAppPreview() {
    SuperheroesAppTheme {
        SuperHeroesApp()
    }
}