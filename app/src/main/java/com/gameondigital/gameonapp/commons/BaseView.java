package com.gameondigital.gameonapp.commons;

import android.os.Bundle;

import androidx.annotation.Nullable;

public interface BaseView {

    @Nullable
    Bundle getExtras();

    void showLoading();

    void dismissLoading();

    void showDialogNonAvailable(boolean finish);

    void showMessage(String title, String message, final boolean finish);

    void showMessage(String message, final boolean finish);

    void finishOnAlwaysError();
}
