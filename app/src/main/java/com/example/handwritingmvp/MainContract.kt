package com.example.handwritingmvp

import android.net.Uri

interface MainContract {
    interface View {
        fun showDeleteDialog()
        fun hideDeleteDialog()
        fun openImagePicker()
        fun showSelectedImage(savedUri: Uri?)
    }

    interface Presenter {
        fun onDeleteClicked()
        fun onConfirmDelete(withImage: Boolean)
        fun closeDeleteDialog()
        fun onPickedImageClicked()
        fun onImagePicked(selectedUri: Uri?)
    }
}