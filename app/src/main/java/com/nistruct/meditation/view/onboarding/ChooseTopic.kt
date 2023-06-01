package com.nistruct.meditation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.nistruct.meditation.DesignContent.MainTitle
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.TopicModel
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.viewmodel.TopicViewModel

@Composable
fun ChooseTopic(navController: NavHostController) {
    var viewModel = hiltViewModel<TopicViewModel>()

    var topics = viewModel.topics.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.choose_topic),
            contentDescription = null,
            contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize()
        )
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(top = 30.dp)) {


            MainTitle(title = "What Brings you", color = Black, align = TextAlign.Start)

            Text(
                text = "to Silent Moon?",
                color = Black,
                fontSize = 28.sp,
                modifier = Modifier.padding(start = 20.dp)
            )

            TitleDetail(text = "choose a topic to focus on:", color = Gray, align = TextAlign.Start)

            topics.value?.let { topicList ->
                Topics(
                    topics = topicList, navController
                )
            }

        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Topics(topics: Array<TopicModel>, navController: NavHostController) {

    Column(modifier = Modifier.fillMaxWidth()) {


        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(topics.size) { item ->
                TopicsItem(topic = topics[item], navController)
            }
        }

    }
}

@Composable
fun TopicsItem(topic: TopicModel, navController: NavHostController) {

    Box(
        modifier = Modifier
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val painter = rememberImagePainter(topic.imageUrl)
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("Reminders/${topic.name}")
                },
            contentScale = ContentScale.FillBounds,
        )
    }
}



