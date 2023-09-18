package com.example.gallaryapp.localData

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalDataSource(var context: Context) : ILocalDataSource {
    override suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>> {
        val imageUris = mutableListOf<Uri>()

        val contentResolver: ContentResolver = context.contentResolver
        val imageUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATE_TAKEN
        )

        val sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC"

        try {
            val cursor = contentResolver.query(imageUri, projection, null, null, sortOrder)
            cursor?.use {
                while (it.moveToNext()) {
                    val imageId = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    val uri = Uri.withAppendedPath(imageUri, imageId.toString())
                    imageUris.add(uri)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return flowOf(imageUris)
    }

    override suspend fun getVideosPathsFromMediaStore(): Flow<List<String>> {
        val videoPaths = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Video.Thumbnails.DATA)
        val videoUri = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI

        val cursor: Cursor? =
            context.contentResolver.query(videoUri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA)
            while (it.moveToNext()) {
                val imagePath = it.getString(columnIndex)
                videoPaths.add(imagePath)
            }
        }
        return flowOf(videoPaths)
    }
}