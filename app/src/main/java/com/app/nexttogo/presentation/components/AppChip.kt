package com.app.nexttogo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppChip(
    text: String,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = {
                onClick(!isSelected)
            })
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AppText.SubTitle(
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary,
            textAlign = TextAlign.Center,
        )
    }
}