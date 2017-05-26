package sample.qiitaclient

import android.content.Context
import android.widget.Toast

/**
 * Created by ekushida on 2017/05/26.
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}