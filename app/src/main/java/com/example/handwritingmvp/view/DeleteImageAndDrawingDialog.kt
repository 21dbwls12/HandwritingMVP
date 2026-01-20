package com.example.handwritingmvp.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

// 휴지통 버튼을 누르면 나오는 대화상자
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteImageAndDrawingDialog(
    onConfirmWithImage: () -> Unit,
    onConfirmWithoutImage: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        // 삭제 요청을 취소
        onDismissRequest = { onDismiss() },
        confirmButton = {
            // 이미지와 필기 모두 삭제
            DialogButton("예") { onConfirmWithImage() }
            // 필기만 삭제
            DialogButton("아니오") { onConfirmWithoutImage() }
        },
        icon = { Icon(imageVector = Icons.Rounded.Info, contentDescription = null) },
        text = { Text("이미지도 같이 삭제하시겠습니까?") }
    )
}

// 휴지통 버튼을 누르면 표시되는 대화상자 내부 텍스트 버튼 양식
@Composable
private fun DialogButton(dialog: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) { Text((dialog)) }
}