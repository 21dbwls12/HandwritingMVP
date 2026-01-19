package com.example.handwritingmvp.presenter

import android.util.Log
import com.example.handwritingmvp.MainContract

class MainPresenter(private var view: MainContract.View) : MainContract.Presenter {
    // 대화상자 표시 요청
    override fun onDeleteClicked() {
        Log.e("MainPresenter", "대화상자 표시 요청 받음")
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
}