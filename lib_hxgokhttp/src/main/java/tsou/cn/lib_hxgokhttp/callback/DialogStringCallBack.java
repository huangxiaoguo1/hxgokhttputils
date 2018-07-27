package tsou.cn.lib_hxgokhttp.callback;

import android.content.Context;

import tsou.cn.lib_hxgdialog.dialog.AlertDialog;
import tsou.cn.lib_hxgokhttp.R;

/**
 * Created by Administrator on 2018/7/27 0027.
 */

public abstract class DialogStringCallBack extends StringEngineCallBack {

    private AlertDialog dialog;

    @Override
    public void onBefore(Context context) {
        dialog = new AlertDialog.Builder(context)
                .setContentView(R.layout.loading)
                .setCancelable(true)
                .show();
    }

    @Override
    public void onAfter() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onSuccess(String resultJson) {
        onHxgSuccess(resultJson);
    }

    @Override
    public void onFail(Exception e) {
        onHxgFail(e);
    }
    public abstract void onHxgSuccess(String resultJson);

    public abstract void onHxgFail(Exception e);
}
