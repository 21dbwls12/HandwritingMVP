package com.example.handwritingmvp.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun ScaffoldView(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // 저장, 휴지통 기능이 적용된 버튼을 포함한 상단바
        topBar = {ScaffoldTopAppBar()},
        // 이미지 불러오기
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 사진선택도구를 불러오는 기능
                }
            ) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScaffoldTopAppBar() {
    TopAppBar(
        title = { Text("필기를 작성하세요") },
        actions = {
            // 저장 기능(캡처??)
            TopBarIconButtons(Icons.Rounded.Check) { }
            // 삭제 기능(필기, 필기와 이미지)
            TopBarIconButtons(Icons.Rounded.Delete) { }
        }
    )
}

@Composable
private fun TopBarIconButtons(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

// 휴지통 버튼을 누르면 나오는 대화상자
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogForDeleteImage() {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            // 이미지와 필기 모두 삭제
            DialogButton("예") {}
            // 필기만 삭제
            DialogButton("아니오") { }
        },
        icon = { Icon(imageVector = Icons.Rounded.Info, contentDescription = null) },
        text = { Text("이미지도 같이 삭제하시겠습니까?") }
    )
}

@Composable
private fun DialogButton(dialog: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) { Text((dialog)) }
}