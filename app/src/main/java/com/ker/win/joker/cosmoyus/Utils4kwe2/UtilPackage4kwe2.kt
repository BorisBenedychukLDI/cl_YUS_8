package com.ker.win.joker.cosmoyus.Utils4kwe2

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Base64
import android.webkit.ValueCallback
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

object UtilPackage4kwe2 {

    var filePathCallBack4kwe2: ValueCallback<Array<Uri>>? = null
    var uriView4kwe2 = Uri.EMPTY

    fun String.decodeBase64ToString () = String (Base64.decode(this, Base64.DEFAULT))

    fun Context.checkPermissions4kwe2 () {
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }
            }).check()
    }
}