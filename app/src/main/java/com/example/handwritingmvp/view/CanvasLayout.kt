package com.example.handwritingmvp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

// 필기용
@Composable
fun CanvasLayout(
    savingDrawing: (Pair<Path, DrawStyle>) -> Unit,
    allPath: List<Pair<Path, DrawStyle>>
) {
    // point 위치 추적을 위한 State
    var point by remember { mutableStateOf(Offset.Zero) }
    // 새로 그려지는 path 표시하기 위한 points State
    val points = remember { mutableListOf<Offset>() }
    // 새로 그려지고 있는 중인 획 State
    var pathInProgress by remember { mutableStateOf(Path()) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    // 화면을 터치한 순간 실행
                    onDragStart = { offset ->
                        // 터치한 좌표
                        point = offset
                        // 현재 터치된 좌표를 선의 시작점으로 지정
                        points.add(point)
                    },
                    // 드래그 하는 동안 실행
                    onDrag = { _, dragAmount ->
                        // 드래그한 만큼 선에 추가
                        point += dragAmount
                        points.add(point)
                        // onDrag가 호출될 때마다 바로 그려지는 획 표시
                        pathInProgress = Path()
                        points.forEachIndexed { index, point ->
                            // 만약 드래그를 처음 시작한다면(처음 클릭한다면)
                            if (index == 0) {
                                // 획의 시작 지점을 해당 좌표로 옮기기
                                pathInProgress.moveTo(point.x, point.y)
                            } else {
                                // 만약 드래그가 이미 진행되고 있었다면 직선 추가
                                pathInProgress.lineTo(point.x, point.y)
                            }
                        }
                    },
                    // 화면에서 손을 떼면 실행
                    onDragEnd = {
                        // 작성된 필기를 model에 저장하기 위해 presenter에 요청
                        savingDrawing(Pair(pathInProgress, Stroke(1f)))
                        // 필기 관련 변수 초기화
                        points.clear()
                        pathInProgress = Path()
                    },
                )
            }
    ) {
        // 반복문 통해서 가장 먼저 그린 선부터 순차적으로 작성한 필기를 화면에 표시
        allPath.forEach {
            drawPath(path = it.first, Color.Black, style = it.second)
        }

        // 드래그하는 동안 해당 선을 화면에 실시간으로 표시
        drawPath(path = pathInProgress, color = Color.Black, style = Stroke(1f))
    }
}