package silicon.thirumanam.com.thirumanam.Common

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import silicon.thirumanam.com.thirumanam.LoginFragment

fun Context.showMsg(msg: String,len:Int=Toast.LENGTH_SHORT){
Toast.makeText(this,msg,len).show();
}
fun Fragment.showMsg(msg: String,len:Int=Toast.LENGTH_SHORT){
    Toast.makeText(context,msg,len).show();
}
fun Fragment.Logd(msg1: String,msg2: String){
    Log.d(msg1,msg2)
}

fun Context.displayError(t: Throwable){
    Toast.makeText(this,"Could not connect to Server" ,Toast.LENGTH_LONG).show()
    Log.d(LoginFragment.TAG, "onFailure t.localizedMessage "+t.localizedMessage )
    Log.d(LoginFragment.TAG, "onFailure t.message"+t.message )
    Log.d(LoginFragment.TAG, "onFailure t.stackTrace"+t.stackTrace )
    Log.d(LoginFragment.TAG, "onFailure t.cause" +t.cause )
    Log.d(LoginFragment.TAG, "onFailure t.printStackTrace()"+t.printStackTrace() )

}