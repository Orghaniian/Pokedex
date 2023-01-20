package com.orghaniian.pokedex.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.utils.getIconResourceId
import com.orghaniian.pokedex.ui.utils.stringResourceID

@Composable
fun TypeChip(
    type: Type
) {
    Surface(
        color = colorResource(R.color.type_chip_background),
        contentColor = colorResource(R.color.on_type),
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius))
    ) {
        val name = stringResource(type.stringResourceID).capitalize(Locale.current)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(dimensionResource(R.dimen.type_chip_height))
                .padding(
                    horizontal = dimensionResource(R.dimen.type_chip_padding_horizontal)
                ),
        ) {
            Icon(
                painter = painterResource(type.getIconResourceId()),
                contentDescription = stringResource(
                    R.string.pokemon_type_image_content_description,
                    name
                ),
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.type_chip_spacing))
                    .size(dimensionResource(R.dimen.type_chip_icon_size))
            )
            Text(
                text = name,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTypeChip() {
    MaterialTheme {
        TypeChip(Type.GRASS)
    }
}