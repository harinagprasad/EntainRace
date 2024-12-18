package com.app.nexttogo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Stable
@Immutable
object AppText {

    @Stable
    @Composable
    fun Headline(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.primary,
        textAlign: TextAlign = TextAlign.Start,
        overflow: TextOverflow = TextOverflow.Visible,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = color,
            textAlign = textAlign,
            modifier = modifier,
            overflow = overflow,
            maxLines = maxLines,
        )
    }

    @Stable
    @Composable
    fun Title(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.primary,
        textAlign: TextAlign = TextAlign.Start,
        overflow: TextOverflow = TextOverflow.Visible,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = color,
            textAlign = textAlign,
            modifier = modifier,
            overflow = overflow,
            maxLines = maxLines,
        )
    }

    @Stable
    @Composable
    fun SubTitle(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.primary,
        textAlign: TextAlign = TextAlign.Start,
        overflow: TextOverflow = TextOverflow.Visible,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = color,
            textAlign = textAlign,
            modifier = modifier,
            overflow = overflow,
            maxLines = maxLines,
        )
    }

    @Stable
    @Composable
    fun Body(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.primary,
        textAlign: TextAlign = TextAlign.Start,
        overflow: TextOverflow = TextOverflow.Visible,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            textAlign = textAlign,
            modifier = modifier,
            overflow = overflow,
            maxLines = maxLines,
        )
    }

    @Stable
    @Composable
    fun Label(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.primary,
        textAlign: TextAlign = TextAlign.Start,
        overflow: TextOverflow = TextOverflow.Visible,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = color,
            textAlign = textAlign,
            modifier = modifier,
            overflow = overflow,
            maxLines = maxLines,
        )
    }

    @Composable
    fun CircularTextView(text: String) {
        val modifier = Modifier
            .size(42.dp)// Set the size of the circle
            .clip(CircleShape) // Make it a circle
            .border(1.5.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
            .padding(8.dp) // Padding inside the circle

        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}