package demo.democommon

import com.bumptech.glide.load.HttpException
import java.io.IOException

fun getException(e : Exception): String {
   return when (e) {
      is HttpException -> e.localizedMessage?:"unknown error"
      is IOException -> e.localizedMessage?:"Check Your Internet Connection"
      else -> e.localizedMessage?:""
   }
}