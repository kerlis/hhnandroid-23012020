package me.doapps.appdhn.dialogs;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.doapps.appdhn.R;

public class DialogConnection extends AlertDialog {

    private ProgressBar progressBar;
    private TextView messageText;
    private Button acceptButton;
    private Button retryButton;
    private onDialogClick onDialogClick;

    public DialogConnection(Context context) {
        super(context);
        initDialog();
    }

    public DialogConnection(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    public DialogConnection(Context context, int themeResId) {
        super(context, themeResId);
        initDialog();
    }

    private void initDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_connection, null);
        setView(view);
        progressBar = view.findViewById(R.id.progressBar);
        messageText = view.findViewById(R.id.message_text);
        acceptButton = view.findViewById(R.id.accept_btn);
        retryButton = view.findViewById(R.id.retry_btn);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogClick.onAccept();
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogClick.onRetry();
            }
        });
    }

    public void setOnDialogClick(DialogConnection.onDialogClick onDialogClick) {
        this.onDialogClick = onDialogClick;
    }

    public void showLoadedMessage(String message){
        messageText.setText(message);
        progressBar.setVisibility(View.GONE);
        acceptButton.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
    }

    public void showLoadMessage(String message){
        messageText.setText(message);
        progressBar.setVisibility(View.VISIBLE);
        acceptButton.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
    }

    public interface onDialogClick{
        void onAccept();
        void onRetry();
    }
}