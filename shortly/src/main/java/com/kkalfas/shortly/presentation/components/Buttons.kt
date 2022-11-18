package com.kkalfas.shortly.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.presentation.theme.ShortlyTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.height(48.dp),
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            disabledBackgroundColor = Color.DarkGray,
            disabledContentColor = Color.Gray,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),

        )
    }
}

@Preview
@Composable
private fun PreviewPrimaryButton() {
    ShortlyTheme {
        Column {
            PrimaryButton(
                label = "shorten it!",
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton(
                label = "shorten it!",
                enabled = false,
                onClick = {}
            )
        }
    }
}
