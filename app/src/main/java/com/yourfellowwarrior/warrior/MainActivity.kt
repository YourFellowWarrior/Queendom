package com.yourfellowwarrior.warrior

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val localView = LocalView.current
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(
                        enabled = true,
                        state = ScrollState(initial = 0)
                    )
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    MaybeTime(fontSize = 18.sp, fontColor = Color.White)
                }
                Row(modifier = Modifier.padding(start = 10.dp)) {
                    MaybeText(
                        text = "Fresh Fan Arts",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontColor = Color.White
                    )
                    MaybeSpacer(modifier = Modifier.width(3.dp))
                    MaybeIcon(
                        icon = Icons.Filled.Info,
                        iconDescription = "Info About Fresh Fan Arts, These Fan Arts Are Manually Updated On Daily Basis By Your Fellow Warrior.",
                        onClick = {
                            maybeSnackBar(
                                localView,
                                "These Fan Arts Are Manually Updated On Daily Basis By Your Fellow Warrior\uD83D\uDC4A\uD83C\uDFFD"
                            )
                        }, modifier = Modifier
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp)
                ) {
                    MaybeImageCard(
                            onClick = { maybeSnackBar(localView,"Working Pretty Fine!") },
                            cardModifier = Modifier.size(150.dp),
                            boxModifier = Modifier.fillMaxSize(),
                            imageURL = "https://cdn.bio.link/uploads/profile_pictures/2021-07-19/Q7m6oO7NBVuTxAltff25NRt9aGqRjYTZ.png",
                            imageDescription = "Checking",
                        imageCrossFade = true
                        )
                }/*https://cdn.bio.link/uploads/profile_pictures/2021-07-19/Q7m6oO7NBVuTxAltff25NRt9aGqRjYTZ.png*/
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun MaybeTime(fontSize: TextUnit, fontColor: Color) {
    when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 0 until 12 -> MaybeText(
            text = "Oi! Good Morning",
            fontSize = fontSize,
            fontColor = fontColor
        )
        in 12 until 16 -> MaybeText(
            text = "Oi! Good Afternoon",
            fontSize = fontSize,
            fontColor = fontColor
        )
        in 16 until 23 -> MaybeText(
            text = "Oi! Good Evening",
            fontSize = fontSize,
            fontColor = fontColor
        )
        in 23 until 24 -> MaybeText(
            text = "Oi! Go And Sleep\uD83D\uDE1C",
            fontSize = fontSize,
            fontColor = fontColor
        )
    }
}

fun maybeSnackBar(view: View, snackBarText: String) {
    Snackbar.make(view, snackBarText, Snackbar.LENGTH_SHORT).show()
}

@Composable
fun MaybeIcon(
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = iconDescription, modifier = modifier)
    }
}

@Composable
fun MaybeSpacer(modifier: Modifier) {
    Spacer(modifier = modifier)
}

@Composable
fun MaybeText(
    text: String,
    modifier: Modifier = Modifier,
    fontColor: Color = Color.White,
    fontSize: TextUnit,
    fontWeight: FontWeight? = FontWeight.Normal,
    fontFamily: GenericFontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
) {
    Text(
        text = text,
        modifier = modifier,
        color = fontColor,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight
    )
}

@ExperimentalMaterialApi
@Composable
fun MaybeImageCard(
    onClick: () -> Unit,
    cardModifier: Modifier,
    boxModifier: Modifier,
    imageURL: String,
    imageDescription: String,
    imageCrossFade: Boolean
) {
    Card(onClick = onClick, modifier = cardModifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = boxModifier) {
                MaybeFetchingImage(
                    imageURL = imageURL,
                    imageDescription = imageDescription,
                    modifier = Modifier.fillMaxSize(),
                    crossFade = imageCrossFade
                )
            }
        }
    }
}

@Composable
fun MaybeFetchingImage(
    imageURL: String,
    imageDescription: String,
    modifier: Modifier,
    crossFade: Boolean
) {
    Image(painter = rememberImagePainter(
        data = imageURL,
        builder = {
            crossfade(crossFade)
        }
    ), contentDescription = imageDescription, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}