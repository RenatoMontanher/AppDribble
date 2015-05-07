package renato.com.br.dribbbleapp.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import renato.com.br.dribbbleapp.R;


@EFragment(R.layout.dialog_info)
public class InfoDialog extends DialogFragment {

    @ViewById
    TextView dialogInfoMessage;

    @FragmentArg
    String infoMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }

    @AfterViews
    void AfterViews() {
        dialogInfoMessage.setText(infoMessage);
    }

    @Click(R.id.dialog_info_ok)
    void onOkClick() {
        dismiss();
    }
}
