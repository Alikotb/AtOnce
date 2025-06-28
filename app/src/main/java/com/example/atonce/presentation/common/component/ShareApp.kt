package com.example.atonce.presentation.common.component

import android.content.Context
import android.content.Intent

fun ShareApp(text: String): Intent {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    return Intent.createChooser(sendIntent, null)


//    Button(onClick = {
//        startActivity(context, shareIntent, null)
//    }) {
//        Icon(imageVector = Icons.Default.Share, contentDescription = null)
//        Text("Share", modifier = Modifier.padding(start = 8.dp))
//    }
}