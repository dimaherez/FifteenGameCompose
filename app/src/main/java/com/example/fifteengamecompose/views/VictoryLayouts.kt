package com.example.fifteengamecompose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifteengamecompose.R
import com.example.fifteengamecompose.ui.theme.Brown
import com.example.fifteengamecompose.ui.theme.RedShadow
import com.example.fifteengamecompose.ui.theme.innerShadow

@Composable
fun PortraitVictoryLayout(movesCounter: Int = 0, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        // To hide control panel
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(340.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.wood),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { }
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    val colors = listOf(
                        Color.Black, Color.Transparent
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors, startY = 1700f, endY = 0.0f),
                        blendMode = BlendMode.DstIn
                    )
                }
                .fillMaxSize()
                .innerShadow(
                    blur = 1.dp,
                    color = Brown,
                    cornersRadius = 0.dp,
                    offsetX = (-5).dp,
                    offsetY = (-5).dp
                )
                .innerShadow(
                    blur = 50.dp,
                    color = RedShadow,
                    cornersRadius = 0.dp,
                    offsetX = 0.5.dp,
                    offsetY = 0.5.dp
                )
        )

        Column(
            modifier = Modifier
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

            ) {
            VictoryTextAndButton(movesCounter = movesCounter, onClick)
        }
    }
}

@Composable
fun LandscapeVictoryLayout(movesCounter: Int = 0, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {

        // To hide control panel
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(400.dp)
                .fillMaxHeight()
                .graphicsLayer {
                    rotationZ = -90f
                }
        )

        Image(
            painter = painterResource(id = R.drawable.wood),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { }
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    val colors = listOf(
                        Color.Black, Color.Transparent
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.horizontalGradient(colors, startX = 1700f, endX = 0.0f),
                        blendMode = BlendMode.DstIn
                    )
                }
                .fillMaxSize()
                .innerShadow(
                    blur = 1.dp,
                    color = Brown,
                    cornersRadius = 0.dp,
                    offsetX = (-5).dp,
                    offsetY = (-5).dp
                )
                .innerShadow(
                    blur = 50.dp,
                    color = RedShadow,
                    cornersRadius = 0.dp,
                    offsetX = 0.5.dp,
                    offsetY = 0.5.dp
                )
        )

        Column(
            modifier = Modifier
                .padding(end = 150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

            ) {
            VictoryTextAndButton(movesCounter = movesCounter, onClick)
        }

    }
}

@Composable
fun VictoryTextAndButton(movesCounter: Int, onClick: () -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 20.dp),
        text = "YOU WON!",
        color = Brown,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
    )
    Text(
        modifier = Modifier.padding(bottom = 20.dp),
        text = "In $movesCounter ${if (movesCounter == 1) "move" else "moves"}",
        color = Brown,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold
    )
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(90.dp)
            .width(180.dp)
            .padding(3.dp)
            .shadow(10.dp),
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
                text = "New Game",
                color = Brown,
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

@Preview(showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun VictoryPreview() {
    PortraitVictoryLayout()
}