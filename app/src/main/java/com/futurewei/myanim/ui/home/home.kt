/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.futurewei.myanim.ui.home


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futurewei.myanim.R
import com.futurewei.myanim.ui.component.HomeFloatingActionButton
import com.futurewei.myanim.ui.component.HomeTabBar
import com.futurewei.myanim.ui.component.TabPage
import com.futurewei.myanim.ui.component.isScrollingUp
import com.futurewei.myanim.ui.component.message.EditMessage
import com.futurewei.myanim.ui.component.message.Header
import com.futurewei.myanim.ui.component.task.TaskRow
import com.futurewei.myanim.ui.component.topic.TopicRow
import com.futurewei.myanim.ui.theme.Green300
import com.futurewei.myanim.ui.theme.Purple100
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Home(){

    val allTasks = stringArrayResource(R.array.tasks)
    val allTopics = stringArrayResource(R.array.topics).toList()

    // The currently selected tab.
    var tabPage by remember { mutableStateOf(TabPage.Home) }

    // True if the whether data is currently loading.
//    var weatherLoading by remember { mutableStateOf(false) }

    // Holds all the tasks currently shown on the task list.
    val tasks = remember { mutableStateListOf(*allTasks) }

    // Holds the topic that is currently expanded to show its body.
    var expandedTopic by remember { mutableStateOf<String?>(null) }

    // True if the message about the edit feature is shown.
    var editMessageShown by remember { mutableStateOf(false) }

//    // Simulates loading weather data. This takes 3 seconds.
//    suspend fun loadWeather() {
//        if (!weatherLoading) {
//            weatherLoading = true
//            delay(3000L)
//            weatherLoading = false
//        }
//    }

    // Shows the message about edit feature.
    suspend fun showEditMessage() {
        if (!editMessageShown) {
            editMessageShown = true
            delay(3000L)
            editMessageShown = false
        }
    }

//    // Load the weather at the initial composition.
//    LaunchedEffect(Unit) {
//        loadWeather()
//    }

    val lazyListState = rememberLazyListState()

    // The background color. The value is changed by the current tab.
    // TODO 1: Animate this color change.
    val backgroundColor by animateColorAsState(if(tabPage == TabPage.Home) Purple100 else Green300)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HomeTabBar(
                backgroundColor = backgroundColor,
                tabPage = tabPage,
                onTabSelected = { tabPage = it }
            )
        },
        backgroundColor = backgroundColor,
        floatingActionButton = {
            HomeFloatingActionButton(
                extended = lazyListState.isScrollingUp(),
                onClick = {
                    coroutineScope.launch {
                        showEditMessage()
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier.padding(padding)
        ) {
            // Weather
            item { Header(title = stringResource(R.string.weather)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 2.dp
                ) {
//                    if (weatherLoading) {
//                        LoadingRow()
//                    } else {
//                        WeatherRow(onRefresh = {
//                            coroutineScope.launch {
//                                loadWeather()
//                            }
//                        })
//                    }
                }
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Topics
            item { Header(title = stringResource(R.string.topics)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(allTopics) { topic ->
                TopicRow(
                    topic = topic,
                    expanded = expandedTopic == topic,
                    onClick = {
                        expandedTopic = if (expandedTopic == topic) null else topic
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Tasks
            item { Header(title = stringResource(R.string.tasks)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            if (tasks.isEmpty()) {
                item {
                    TextButton(onClick = { tasks.clear(); tasks.addAll(allTasks) }) {
                        Text(stringResource(R.string.add_tasks))
                    }
                }
            }
            items(count = tasks.size) { i ->
                val task = tasks.getOrNull(i)
                if (task != null) {
                    key(task) {
                        TaskRow(
                            task = task,
                            onRemove = { tasks.remove(task) }
                        )
                    }
                }
            }
        }
        EditMessage(editMessageShown)
    }
}

