package com.example.atonce.presentation.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.common.theme.RegularFont

@Composable
fun OtpIView(
    modifier: Modifier = Modifier,
    digitsCount: Int = 5,
    onComplete: (String) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    var code by remember { mutableStateOf(List(digitsCount) { "" }) }
    val focusRequesters = remember { List(digitsCount) { FocusRequester() } }

    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        val indices = if (isRtl) (digitsCount - 1 downTo 0) else (0 until digitsCount)

        indices.forEach { index ->
            TextField(
                value = code[index],
                onValueChange = {
                    if (it.length <= 1 && it.all { c -> c.isDigit() }) {
                        code = code.toMutableList().also { list -> list[index] = it }

                        if (it.isNotEmpty()) {
                            if (index != indices.last()) {
                                focusRequesters[indices.indexOf(index) + 1].requestFocus()
                            } else {
                                onComplete(code.joinToString(""))
                            }
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .focusRequester(focusRequesters[indices.indexOf(index)])
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        colors.onSurfaceVariant,
                        RoundedCornerShape(12.dp)
                    ),
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = RegularFont,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colors.onPrimary,
                    unfocusedContainerColor = colors.onPrimary,
                    cursorColor = colors.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colors.onBackground,
                    unfocusedTextColor = colors.onBackground
                ),
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}
