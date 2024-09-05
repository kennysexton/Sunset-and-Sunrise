package com.kennysexton.sunset.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kennysexton.sunset.ui.theme.HappyFisherGradientEnd
import com.kennysexton.sunset.ui.theme.HappyFisherGradientStart

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: ButtonColors
    = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    border: BorderStroke? = null,
    content: @Composable (RowScope) -> Unit
) {

    // Call the original Button composable
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        border = border,
        content = content
    )
}

@Composable
fun BlueButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(16.dp)
        .clip(shape = RoundedCornerShape(8.dp))
        .background(
            brush = Brush.linearGradient(
                colors = listOf(HappyFisherGradientStart, HappyFisherGradientEnd)
            )
        ),
    content: @Composable (RowScope) -> Unit,
) {
    DefaultButton(
        shape = RoundedCornerShape(6.dp),
        colors = ButtonColors(containerColor = Color.Transparent, contentColor = Color.White, disabledContentColor = Color.Transparent, disabledContainerColor = Color.White),
        onClick = onClick,
        modifier = modifier,
        content = content
    )
}


@Preview
@Composable
fun BlueButtonPreview() {
    BlueButton(onClick = {}) {
        Text(text = "Blue button")
    }

}
