package com.example.handwritingmvp.model

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ImageModel {
    // 저장된 사진 Uri
    var savedUri by mutableStateOf<Uri?>(null)
        // uri값이 외부에서 수정하지 못하도록 setter로 설정
        private set

    // 저장된 사진 uri를 변경하여 저장
    fun updateUri(uri:Uri) {
        savedUri = uri
    }

    // 저장된 사진 uri 삭제
    fun deleteUri() {
        savedUri = null
    }

    // 요청받은 데이터 전달
    fun sendUri(): Uri? {
        return savedUri
    }
}