package com.example.handwritingmvp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

// 필기용
@Composable
fun CanvasLayout(onDragStart: () -> Unit, onDrag: () -> Unit, onDragEnd: () -> Unit) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    // 화면을 터치한 순간 실행
                    onDragStart = { offset -> onDragStart() },
                    // 드래그 하는 동안 실행
                    onDrag = { _, dragAmount -> onDrag() },
                    // 화면에서 손을 떼면 실행
                    onDragEnd = { onDragEnd() },
                )
            }
    ) { }
}