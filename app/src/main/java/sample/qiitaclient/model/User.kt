package sample.qiitaclient.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ekushida on 2017/05/22.
 */

/// データクラス
data class User(val id: String,
                val name: String,
                val profileImageUrl: String) : Parcelable {

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<User> = object  : Parcelable.Creator<User> {

            override fun createFromParcel(source: Parcel?): User = source.run {
                User(readString(), readString(), readString())
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)

            override fun de
        }
    }
}