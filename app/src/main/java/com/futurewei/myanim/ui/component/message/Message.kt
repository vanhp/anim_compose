package com.futurewei.myanim.ui.component.message

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.futurewei.myanim.R



/**
 * Shows the header label.
 *
 * @param title The title to be shown.
 */
@Composable
fun Header(
    title: String
) {
    Text(
        text = title,
        modifier = Modifier.semantics { heading() },
        style = MaterialTheme.typography.h5
    )
}


/**
 * Shows a message that the edit feature is not available.
 */
@Composable
fun EditMessage(shown: Boolean) {
    // TODO 2-2: The message should slide down from the top on appearance and slide up on
    //           disappearance.
    AnimatedVisibility(
        visible = shown,
        // slide from 0 to -fullheight
        enter = slideInVertically(
            initialOffsetY = {fullHeight -> -fullHeight  },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        // slide from -fullheight to 0
        exit = slideOutVertically (
            targetOffsetY = {fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Text(
                text = stringResource(R.string.edit_message),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}