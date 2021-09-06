package com.yourfellowwarrior.warrior

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.hours

class MainActivity : ComponentActivity() {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}