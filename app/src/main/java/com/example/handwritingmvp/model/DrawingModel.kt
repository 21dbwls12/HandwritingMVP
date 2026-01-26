package com.example.handwritingmvp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle

class DrawingModel {
    // 필기 데이터
    // ArrayDeque를 Stack으로 이용(나중에 되돌리기 기능 추가)
    var savedPath by mutableStateOf(ArrayDeque<Pair<Path, DrawStyle>>())
        private set

    // 요청받은 데이터 전달
    fun sendPath(): ArrayDeque<Pair<Path, DrawStyle>> {
        return savedPath
    }
}