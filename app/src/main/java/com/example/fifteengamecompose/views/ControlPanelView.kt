package com.example.fifteengamecompose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifteengamecompose.FifteenIntent
import com.example.fifteengamecompose.R
import com.example.fifteengamecompose.ui.theme.Brown
import com.example.fifteengamecompose.ui.theme.RedShadow
import com.example.fifteengamecompose.ui.theme.innerShadow

@Composable
fun ControlPanelView(movesCounter: Int, onAction: (FifteenIntent) -> Unit) {
    Column(
        modifier = Modifier.height(190.dp), // 90 + 90 + 10 for padding
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .height(90.dp)
                .width(320.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.wood),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .innerShadow(
                        blur = 1.dp,
                        color = Brown,
                        cornersRadius = 10.dp,
                        offsetX = (-5).dp,
                        offsetY = (-5).dp
                    )
                    .innerShadow(
                        blur = 20.dp,
                        color = RedShadow,
                        cornersRadius = 10.dp,
                        offsetX = 0.5.dp,
                        offsetY = 0.5.dp
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
            )

            Text(
                text = "Moves: $movesCounter",
                color = Brown,
                fontSize = 40.sp,
            )
        }

        Button(
            modifier = Modifier
                .height(90.dp)
                .width(320.dp),
            onClick = { onAction(FifteenIntent.Reset) },
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.wood),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .innerShadow(
                            blur = 1.dp,
                            color = Brown,
                            cornersRadius = 10.dp,
                            offsetX = (-5).dp,
                            offsetY = (-5).dp
                        )
                        .innerShadow(
                            blur = 20.dp,
                            color = RedShadow,
                            cornersRadius = 10.dp,
                            offsetX = 0.5.dp,
                            offsetY = 0.5.dp
                        )
                )
                Text(
                    text = "Restart",
                    color = Brown,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}