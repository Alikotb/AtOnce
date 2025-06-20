package com.example.atonce.presentation.common.component


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.FontSizes.LOGINTXT
import com.example.atonce.presentation.common.theme.MediumFont
import com.example.atonce.presentation.common.theme.RegularFont


@Composable
fun LoginPasswordTF(
    txt: String = stringResource(R.string.password),
    msg: String = "***************",
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    val colors = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = txt,
            fontSize = LOGINTXT.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            color = colors.onBackground,
            fontFamily = MediumFont,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(
                    color = colors.onSurfaceVariant,
                    width = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                ),
            value = text,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = {
                Text(
                    text = msg,
                    fontFamily = RegularFont,
                    fontSize = LOGINTXT.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            textStyle = LocalTextStyle.current.copy(
                fontFamily = RegularFont,
                fontSize = 14.sp
            ),
            onValueChange = { it ->
                text = it
                onValueChange(it.text)
            },
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors.onPrimary,
                unfocusedContainerColor = colors.onPrimary,
                cursorColor = colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colors.onBackground,
                unfocusedTextColor = colors.onBackground
            ),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    Icon(
                        imageVector = if (passwordHidden) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (passwordHidden) "Show password" else "Hide password"
                    )
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoginTF(
    txt: String = stringResource(R.string.email),
    msg: String = "ali@gmail.com",
    onValueChange: (String) -> Unit = {},
    isIcon: Boolean = false,
    firstIcon: ImageVector = Icons.Filled.ArrowDropDown,
    secondIcon: ImageVector = Icons.Filled.ArrowDropUp,
    onIconClick: () -> Unit = {}
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var changeIcon by rememberSaveable { mutableStateOf(true) }
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = txt,
            fontSize = LOGINTXT.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            color = colors.onBackground,
            fontFamily = MediumFont,

            )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(
                    color = colors.onSurfaceVariant,
                    width = 2.dp,
                    shape = RoundedCornerShape(12.dp)
                ),
            value = text,
            placeholder = {
                Text(
                    text = msg,
                    fontFamily = RegularFont,
                    fontSize = LOGINTXT.sp
                )

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            onValueChange = { it ->
                text = it
                onValueChange(it.text)
            },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = RegularFont,
                fontSize = 14.sp
            ),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                cursorColor = colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colors.onBackground,
                unfocusedTextColor = colors.onBackground
            ),

            trailingIcon = {
                if (isIcon) {
                    IconButton(onClick = {
                        changeIcon = !changeIcon
                        onIconClick()
                    }) {
                        Icon(
                            imageVector = if (changeIcon) firstIcon else secondIcon,
                            contentDescription = "nill"
                        )
                    }
                }

            }
        )
    }
}