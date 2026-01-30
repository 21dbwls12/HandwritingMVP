package com.example.handwritingmvp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import java.util.ArrayDeque

class DrawingModel {
    // 필기 데이터
    // ArrayDeque를 Stack으로 이용(나중에 되돌리기 기능 추가)
    // kotlin.collections.ArrayDeque -> MutableList(pop, push, ... 사용 불가)
    // java.util.ArrayDeque -> Deque(pop, push, ... 사용 가능)
    var savedPath by mutableStateOf(ArrayDeque<Pair<Path, DrawStyle>>())
        private set

    // 사용자 필기 저장
    fun updatePath(newPath: Pair<Path, DrawStyle>) {
        savedPath.push(newPath)
    }

    // 요청받은 데이터 전달
    fun sendPath(): ArrayDeque<Pair<Path, DrawStyle>> {
        return savedPath
    }
}