package com.example.handwritingmvp

interface MainContract {
    interface View {
//        fun showDeleteDialog()
//        fun hideDeleteDialog()
//        fun updateImage()
    }

    interface Presenter {
        fun onDeleteClicked()
        fun onConfirmDelete(withImage: Boolean)
    }
}