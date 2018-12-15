package com.gameondigital.gameonapp.MyProfile.StaticsMyProfile;

public class StaticsMyProfilePresenter implements StaticsMyProfileContract.UserActionsListener {

    private StaticsMyProfileContract.View mStaticsMyProfileView;

    public StaticsMyProfilePresenter(StaticsMyProfileContract.View staticsMyProfileView) {
        mStaticsMyProfileView = staticsMyProfileView;
    }
}
