package com.example.atonce.presentation.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.comon.FontSizes.LOGINTXT


@Composable
fun LoginPasswordTF(txt: String = "Password", msg :String = "***************" ,onValueChange: (String) -> Unit ={}){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    Column (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = txt,
            fontSize = LOGINTXT.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            value = text,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = { Text(text = msg) },
            keyboardOptions = KeyboardOptions(
                keyboardType =  KeyboardType.Password ),
            onValueChange = { it ->
                text = it
                onValueChange(it.text)
            },
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0),
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
fun LoginTF(txt: String = "Email", msg :String = "ali@gmail.com" ,
            onValueChange: (String) -> Unit ={}, isIcon : Boolean = false,
            firstIcon: ImageVector=Icons.Filled.ArrowDropDown,secoundIcon : ImageVector=Icons.Filled.ArrowDropUp,
            onIconClick: () -> Unit ={} ){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var changeIcon by rememberSaveable { mutableStateOf(true) }

    Column (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = txt,
            fontSize = LOGINTXT.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            value = text,
            placeholder = { Text(text = msg) },
            keyboardOptions = KeyboardOptions(
                keyboardType =  KeyboardType.Text),
            onValueChange = { it ->
                text = it
                onValueChange(it.text)
            },
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0),
            ),

            trailingIcon = {
                if(isIcon){
                    IconButton(onClick = {
                        changeIcon = !changeIcon
                        onIconClick()
                    }) {
                        Icon(
                            imageVector = if (changeIcon) firstIcon else secoundIcon,
                            contentDescription = "nill"
                        )
                    }
                }

            }
        )
    }
}