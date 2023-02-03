package com.orghaniian.pokedex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.theme.Dimensions
import com.orghaniian.pokedex.ui.theme.PokedexColor
import com.orghaniian.pokedex.ui.utils.getIconResourceId
import com.orghaniian.pokedex.ui.utils.stringResourceID

@Composable
fun TypeChip(
    type: Type
) {
    Surface(
        color = PokedexColor.typeChipBackground,
        contentColor = PokedexColor.onType,
        shape = RoundedCornerShape(Dimensions.cornerRadius)
    ) {
        val name = stringResource(type.stringResourceID).capitalize(Locale.current)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(Dimensions.typeChipHeight)
                .padding(
                    horizontal = Dimensions.typeChipPaddingHorizontal
                ),
        ) {
            Icon(
                painter = painterResource(type.getIconResourceId()),
                contentDescription = stringResource(
                    R.string.pokemon_type_image_content_description,
                    name
                ),
                modifier = Modifier
                    .padding(end = Dimensions.typeChipSpacing)
                    .size(Dimensions.typeChipIconSize)
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