package com.example.supreheroes

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supreheroes.model.Hero
import com.example.supreheroes.model.HeroesRepository

@SuppressLint("ResourceType")
@Composable
fun HeroItem(modifier: Modifier = Modifier, hero: Hero) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(dimensionResource(id = R.dimen.image_size))
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .sizeIn(minHeight = 78.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopCenter
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HeroesAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    })


}

@Composable
fun HeroesItemsList(modifier: Modifier = Modifier) {
    val list = HeroesRepository.heroes
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        items(list) { hero ->
            HeroItem(
                hero = hero,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeroItem() {
    HeroItem(
        modifier = Modifier.padding(2.dp),
        Hero(R.string.hero2, R.string.description2, R.drawable.android_superhero2)
    )
}