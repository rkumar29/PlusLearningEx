package pluslearning.bailiwick.rajesh.com.pluslearning.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Deswal on 30-01-2018.
 */

public class ProDilog {
    private ProgressDialog dialog;
    private static ProDilog mInstance;

    public static synchronized ProDilog getInstance() {
        if (mInstance == null) {
            mInstance = new ProDilog();
        }
        return mInstance;
    }

    public void show(Context context, String message) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
