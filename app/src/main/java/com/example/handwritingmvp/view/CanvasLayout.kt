package com.example.handwritingmvp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.input.pointer.pointerInput

// 필기용
@Composable
fun CanvasLayout(onDragStart: () -> Unit, onDrag: () -> Unit, saveDrawing: () -> Unit, allPath: List<Pair<Path, DrawStyle>>) {
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
                    onDragEnd = { saveDrawing() },
                )
            }
    ) {
        // 반복문 통해서 가장 먼저 그린 선부터 순차적으로 작성한 필기를 화면에 표시
        allPath.forEach {
            drawPath(path = it.first, Color.White, style = it.second)
        }
    }
}