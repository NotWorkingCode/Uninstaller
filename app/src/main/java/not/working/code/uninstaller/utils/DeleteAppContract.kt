package not.working.code.uninstaller.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class DeleteAppContract : ActivityResultContract<String, Boolean>() {

    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(Intent.ACTION_DELETE)
            .setData(Uri.parse("package:$input"))
    }


    override fun parseResult(resultCode: Int, intent: Intent?) = true
}