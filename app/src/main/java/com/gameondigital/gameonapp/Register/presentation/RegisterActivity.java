package com.gameondigital.gameonapp.Register.presentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Register.interactor.RegisterInteractorImpl;
import com.gameondigital.gameonapp.Register.presenter.RegisterPresenter;
import com.gameondigital.gameonapp.Register.interactor.RegisterInteractor;
import com.gameondigital.gameonapp.Register.presenter.RegisterPresenterImpl;
import com.gameondigital.gameonapp.Utils.ShowToast;
import com.gameondigital.gameonapp.Utils.ValidationFields;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private Typeface montserratBold,
            montserratRegular;

    private TextView txtTitle,
            txtTitleName,
            txtTitleEmail,
            txtTitleCountrys,
            txtTitleStates,
            txtTitlePlatform,
            txtTitleGame,
            txtTitleIdProfile,
            txtTitlePsn,
            txtTitleLive,
            txtTitleSteam,
            txtTitlePassword;
    private EditText edtName,
            edtEmail,
            edtId,
            edtPsn,
            edtLive,
            edtSteam,
            edtPassword;
    private ImageView imgClose,
            imgPsn,
            imgLive,
            imgSteam,
            imgPes,
            imgFifa;
    private LinearLayout llContent,
            llPsn,
            llLive,
            llSteam;
    private Spinner spinnerCountrys,
            spinnerStates;
    private Button btnRegister;

    private boolean psn_click = false,
            live_click = false,
            steam_click = false,
            pes_click = false,
            fifa_click = false;

    private User user;
    private String platformer;
    private String game;

    private RegisterPresenter presenter;
    private RegisterInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initListeners();
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initView() {
        montserratBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.otf");
        montserratRegular = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");

        txtTitle = findViewById(R.id.txt_title_register);
        txtTitleName = findViewById(R.id.txt_title_name_register);
        txtTitleEmail = findViewById(R.id.txt_title_email_register);
        txtTitleCountrys = findViewById(R.id.txt_title_countrys_register);
        txtTitleStates = findViewById(R.id.txt_title_states_register);
        txtTitlePlatform = findViewById(R.id.txt_title_platform_register);
        txtTitleGame = findViewById(R.id.txt_title_game_register);
        txtTitleIdProfile = findViewById(R.id.txt_title_id_profile_register);
        txtTitlePsn = findViewById(R.id.txt_title_psn_register);
        txtTitleLive = findViewById(R.id.txt_title_live_register);
        txtTitleSteam = findViewById(R.id.txt_title_steam_register);
        txtTitlePassword = findViewById(R.id.txt_title_password_register);

        edtName = findViewById(R.id.edt_name_register);
        edtEmail = findViewById(R.id.edt_email_register);
        edtId = findViewById(R.id.edt_id_register);
        edtPsn = findViewById(R.id.edt_psn_register);
        edtLive = findViewById(R.id.edt_live_register);
        edtSteam = findViewById(R.id.edt_steam_register);
        edtPassword = findViewById(R.id.edt_password_register);

        imgClose = findViewById(R.id.img_close_register);
        imgPsn = findViewById(R.id.img_psn_register);
        imgLive = findViewById(R.id.img_live_register);
        imgSteam = findViewById(R.id.img_steam_register);
        imgPes = findViewById(R.id.img_pes_register);
        imgFifa = findViewById(R.id.img_fifa_register);

        llContent = findViewById(R.id.ll_content_register);
        llPsn = findViewById(R.id.ll_psn_register);
        llLive = findViewById(R.id.ll_live_register);
        llSteam = findViewById(R.id.ll_steam_register);

        spinnerCountrys = findViewById(R.id.spinner_countrys_register);
        spinnerStates = findViewById(R.id.spinner_states_register);

        btnRegister = findViewById(R.id.btn_register);
    }

    private void initListeners() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.country_arrays));
        spinnerCountrys.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.states_arrays));
        spinnerStates.setAdapter(adapter2);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intToLogin);
                finish();
            }
        });

        imgPsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (psn_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgPsn.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_off, getTheme()));
                    } else {
                        imgPsn.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_off));
                    }
                    llPsn.setVisibility(View.GONE);
                    psn_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgPsn.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_on, getTheme()));
                    } else {
                        imgPsn.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_on));
                    }
                    llPsn.setVisibility(View.VISIBLE);
                    psn_click = true;
                }
            }
        });

        imgLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (live_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgLive.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_off, getTheme()));
                    } else {
                        imgLive.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_off));
                    }
                    llLive.setVisibility(View.GONE);
                    live_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgLive.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_on, getTheme()));
                    } else {
                        imgLive.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_on));
                    }
                    llLive.setVisibility(View.VISIBLE);
                    live_click = true;
                }
            }
        });

        imgSteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (steam_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgSteam.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_off, getTheme()));
                    } else {
                        imgSteam.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_off));
                    }
                    llSteam.setVisibility(View.GONE);
                    steam_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgSteam.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_on, getTheme()));
                    } else {
                        imgSteam.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_on));
                    }
                    llSteam.setVisibility(View.VISIBLE);
                    steam_click = true;
                }
            }
        });

        imgPes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pes_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgPes.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_off, getTheme()));
                    } else {
                        imgPes.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_off));
                    }
                    pes_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgPes.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_on, getTheme()));
                    } else {
                        imgPes.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_on));
                    }
                    pes_click = true;
                }
            }
        });

        imgFifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fifa_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgFifa.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_off, getTheme()));
                    } else {
                        imgFifa.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_off));
                    }
                    fifa_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imgFifa.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_on, getTheme()));
                    } else {
                        imgFifa.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_on));
                    }
                    fifa_click = true;
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidationFields.isEmpty(edtPassword)) {
                    user = new User();
                    user.setName(edtName.getText().toString());
                    user.setEmail(edtEmail.getText().toString());
                    user.setCountry(spinnerCountrys.getSelectedItem().toString());
                    user.setState(spinnerStates.getSelectedItem().toString());

                    if(psn_click && live_click && steam_click){
                        platformer = "PS3-PS4-Xbox360-XboxOne-PC";
                    } else if (psn_click && live_click){
                        platformer = "PS3-PS4-Xbox360-XboxOne";
                    } else if (psn_click && steam_click){
                        platformer = "PS3-PS4-PC";
                    } else if (live_click && steam_click){
                        platformer = "Xbox360-XboxOne-PC";
                    } else if(psn_click){
                        platformer = "PS3-PS4";
                    } else if(live_click){
                        platformer = "Xbox360-XboxOne";
                    } else if(steam_click){
                        platformer = "PC";
                    }

                    user.setPlatformer(platformer);

                    if(pes_click && fifa_click){
                        game = "PES-FIFA";
                    } else if (pes_click){
                        game = "PES";
                    } else if(fifa_click) {
                        game = "FIFA";
                    }

                    user.setGame(game);
                    user.setId(edtId.getText().toString());
                    user.setPsn(edtPsn.getText().toString());
                    user.setPassword(edtPassword.getText().toString());

                    interactor.register(RegisterActivity.this, user);
                } else {
                    edtPassword.setError("Digite uma senha");
                }
            }
        });
    }

    private void init() {
        presenter = new RegisterPresenterImpl();
        interactor = new RegisterInteractorImpl();

        presenter.setView(this);
        interactor.setPresenter(presenter);

        presenter.setFonts();
    }

    @Override
    public void callLogin() {
        Intent intToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intToLogin);
        finish();
    }

    @Override
    public void showErrorLogin(Task task) {
        String exceptionError = "";
        try {
            throw task.getException();
        } catch (FirebaseAuthWeakPasswordException e) {
            exceptionError = "A senha deve conter no mínimo 8 caracteres de letras e números";
        }
        catch (FirebaseAuthInvalidCredentialsException e) {
            exceptionError = "O e-mail digitado é inválido, digite um novo e-mail";
        }
        catch (FirebaseAuthUserCollisionException e) {
            exceptionError = "E-mail já cadastrado";
        }
        catch (Exception e){
            exceptionError = "Erro ao efetuar o cadastro";
            e.printStackTrace();
        }
        ShowToast.toast(this, exceptionError);
    }

    @Override
    public void setFonts() {
        txtTitle.setTypeface(montserratBold);
        txtTitleName.setTypeface(montserratBold);
        txtTitleEmail.setTypeface(montserratBold);
        txtTitleCountrys.setTypeface(montserratBold);
        txtTitleStates.setTypeface(montserratBold);
        txtTitlePlatform.setTypeface(montserratBold);
        txtTitleGame.setTypeface(montserratBold);
        txtTitleIdProfile.setTypeface(montserratBold);
        txtTitlePsn.setTypeface(montserratBold);
        txtTitleLive.setTypeface(montserratBold);
        txtTitleSteam.setTypeface(montserratBold);
        txtTitlePassword.setTypeface(montserratBold);

        edtName.setTypeface(montserratRegular);
        edtEmail.setTypeface(montserratRegular);
        edtId.setTypeface(montserratRegular);
        edtPsn.setTypeface(montserratRegular);
        edtLive.setTypeface(montserratRegular);
        edtSteam.setTypeface(montserratRegular);
        edtPassword.setTypeface(montserratRegular);

        btnRegister.setTypeface(montserratBold);
    }
}
