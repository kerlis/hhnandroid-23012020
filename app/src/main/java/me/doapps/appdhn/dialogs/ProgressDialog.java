package me.doapps.appdhn.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import me.doapps.appdhn.R;

/**
 * Created by William_ST on 02/10/15.
 */
public class ProgressDialog extends AlertDialog {

    public  ProgressDialog(Context context) {
        super(context);
        initDialog();
    }

    public void initDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_progress, null);
        setView(view);

    }

    public void dismiss(){
        dismiss();
    }
}
