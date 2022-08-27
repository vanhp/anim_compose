package com.futurewei.myanim.ui.component.topic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.futurewei.myanim.R



/**
 * Shows a row for one topic.
 *
 * @param topic The topic title.
 * @param expanded Whether the row should be shown expanded with the topic body.
 * @param onClick Called when the row is clicked.
 */
@ExperimentalMaterialApi
@Composable
fun TopicRow(topic: String, expanded: Boolean, onClick: () -> Unit) {
    TopicRowSpacer(visible = expanded)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        onClick = onClick
    ) {
        // TODO 3: Animate the size change of the content.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize()
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = topic,
                    style = MaterialTheme.typography.body1
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.lorem_ipsum),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
    TopicRowSpacer(visible = expanded)
}


/**
 * Shows a separator for topics.
 */
@Composable
fun TopicRowSpacer(visible: Boolean) {
    AnimatedVisibility(visible = visible) {
        Spacer(modifier = Modifier.height(8.dp))
    }
}
