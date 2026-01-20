package com.example.handwritingmvp

interface MainContract {
    interface View {
        fun showDeleteDialog()
        fun hideDeleteDialog()
    }

    interface Presenter {
        fun onDeleteClicked()
        fun onConfirmDelete(withImage: Boolean)
        fun closeDeleteDialog()
    }
}