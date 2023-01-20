package com.orghaniian.pokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.pokedex.R

@Composable
fun ReturnButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Icon(
        Icons.Rounded.ArrowBack,
        contentDescription = stringResource(R.string.back),
        modifier.clickable(
            onClickLabel = stringResource(R.string.back),
            onClick = onClick,
            role = Role.Button,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                bounded = false,
                radius = dimensionResource(R.dimen.icon_button_ripple_radius)
            )
        )
    )
}

@Preview
@Composable
private fun PreviewReturnButton() {
    MaterialTheme {
        ReturnButton {

        }
    }
}