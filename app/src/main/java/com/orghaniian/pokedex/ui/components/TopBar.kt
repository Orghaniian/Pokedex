package com.orghaniian.pokedex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.pokedex.R


@Composable
fun TopBar(
    title: String,
    onBackPressed: (() -> Unit)?
) {
    Column(
        Modifier
            .statusBarsPadding()
            .padding(dimensionResource(R.dimen.screen_padding))
    ) {
        if(onBackPressed != null) {
            ReturnButton(
                onClick = onBackPressed
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.margin_m))
        )
    }
}

@Composable
@Preview
private fun PreviewTopBar() {
    MaterialTheme {
        TopBar(title = stringResource(R.string.app_name)) {
            
        }
    }
}