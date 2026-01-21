package com.example.handwritingmvp.view

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun NoteLayout(savedUri: Uri?) {
    AsyncImage(
        model = savedUri,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}