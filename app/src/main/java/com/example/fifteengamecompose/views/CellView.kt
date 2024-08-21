package com.example.fifteengamecompose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifteengamecompose.R
import com.example.fifteengamecompose.ui.theme.Brown
import com.example.fifteengamecompose.ui.theme.RedShadow
import com.example.fifteengamecompose.ui.theme.innerShadow

@Composable
fun Cell(number: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .alpha(if (number == " ") 0f else 1f)
            .size(90.dp)
            .padding(3.dp)
            .shadow(10.dp)
            .then(modifier),
        shape = RoundedCornerShape(10.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.wood),
                contentDescription = null,
                modifier = modifier
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
                number,
                color = Brown,
                fontSize = 50.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
            )
        }

    }
}