package com.example.fifteengamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fifteengamecompose.views.ResponsiveLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FifteenGame()
        }
    }
}


@Composable
fun FifteenGame(
    fifteenViewModel: FifteenViewModel = viewModel<FifteenViewModel>()
) {
    Box(
        modifier = with(Modifier) {
            fillMaxSize()
            paint(
                painterResource(id = R.drawable.bg),
                contentScale = ContentScale.FillBounds
            )
        }
    ) {
        ResponsiveLayout(state = fifteenViewModel.state, onAction = fifteenViewModel::processIntent)
    }
}



