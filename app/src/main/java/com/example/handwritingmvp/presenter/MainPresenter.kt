package com.example.handwritingmvp.presenter

import android.net.Uri
import com.example.handwritingmvp.MainContract

class MainPresenter(private var view: MainContract.View) : MainContract.Presenter {
    // 대화상자 표시 요청
    override fun onDeleteClicked() {
        view.showDeleteDialog()
    }

    // 조건 확인해서 model에 데이터 삭제 요청
    override fun onConfirmDelete(withImage: Boolean) {
        if (withImage) {
            // Model에서 이미지와 필기 데이터 모두 null로 변경
        } else {
            // Model에서 필기 데이터만 null로 변경
        }
        // 모든 작업이 끝나면 대화상자 닫기
        view.hideDeleteDialog()
    }

    // 대화상자 닫기 요청
    override fun closeDeleteDialog() {
        view.hideDeleteDialog()
    }

    // 사진 선택 도구 실행 요청
    override fun onPickedImageClicked() {
        view.openImagePicker()
    }

    // 선택한 사진 uri Model에 저장 요청 및 화면에 표시 요청
    override fun onImagePicked(uri: Uri?) {
        // Model에 Uri 저장 요청
        // 화면에 표시 요청
    }
}