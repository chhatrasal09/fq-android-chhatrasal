package com.app.chhatrasal.frequesncy.utils

import java.lang.StringBuilder
import java.security.MessageDigest

class SecureData {
    companion object {

        /**
         * MD5 hashing to encrypt the password using SHA512 algorithm.
         */
        fun encryptData(data: String): String {
            val HEX_CHARS = "0123456789ABCDEF"
            val bytes = MessageDigest.getInstance("SHA512").digest(data.toByteArray())
            val result = StringBuilder(bytes.size * 2)
            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }
            return result.toString()
        }
    }

}