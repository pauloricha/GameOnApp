package com.gameondigital.gameonapp.Register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.gameondigital.gameonapp.Login.presentation.LoginActivity;
import com.gameondigital.gameonapp.Main.MainActivity;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Utils.ValidationFields;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterFragment extends Fragment implements RegisterContract.View {

    private RegisterContract.UserActionsListener mActionsListener;

    private EditText edt_name_register,
            edt_email_register,
            edt_id_register,
            edt_psn_register,
            edt_live_register,
            edt_steam_register,
            edt_password_register;
    private Button btn_register;
    private ImageView img_close_register,
            img_psn_register,
            img_live_register,
            img_steam_register,
            img_pes_register,
            img_fifa_register;
    private Spinner spinner_countrys_register,
            spinner_states_register;
    private boolean psn_click = false,
            live_click = false,
            steam_click = false,
            pes_click = false,
            fifa_click = false;
    private LinearLayout ll_psn_register,
            ll_live_register,
            ll_steam_register,
            ll_activity_register;

    private User user;
    private String platformer;
    private String game;

    View rootView;

    public static RegisterFragment newInstance() {
        Log.i("LOOG", "ProfilePlayerFragment -> newInstance");
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "ProfilePlayerFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ProfilePlayerFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_register, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "ProfilePlayerFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "ProfilePlayerFragment -> initViews");
        img_close_register = rootView.findViewById(R.id.img_close_register);

        edt_name_register = rootView.findViewById(R.id.edt_name_register);
        edt_email_register = rootView.findViewById(R.id.edt_email_register);
        edt_id_register = rootView.findViewById(R.id.edt_id_register);
        edt_psn_register = rootView.findViewById(R.id.edt_psn_register);
        edt_live_register = rootView.findViewById(R.id.edt_live_register);
        edt_steam_register = rootView.findViewById(R.id.edt_steam_register);
        edt_password_register = rootView.findViewById(R.id.edt_password_register);

        img_psn_register = rootView.findViewById(R.id.img_psn_register);
        img_live_register = rootView.findViewById(R.id.img_live_register);
        img_steam_register = rootView.findViewById(R.id.img_steam_register);
        img_pes_register = rootView.findViewById(R.id.img_pes_register);
        img_fifa_register = rootView.findViewById(R.id.img_fifa_register);

        spinner_countrys_register = rootView.findViewById(R.id.spinner_countrys_register);
        spinner_states_register = rootView.findViewById(R.id.spinner_states_register);

        ll_psn_register = rootView.findViewById(R.id.ll_psn_register);
        ll_live_register = rootView.findViewById(R.id.ll_live_register);
        ll_steam_register = rootView.findViewById(R.id.ll_steam_register);
        ll_activity_register = rootView.findViewById(R.id.ll_activity_register);

        btn_register = rootView.findViewById(R.id.btn_register);

        mActionsListener = new RegisterPresenter(this);
    }

    private void initListeners(){
        Log.i("LOOG", "ProfilePlayerFragment -> initListeners");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.country_arrays));
        spinner_countrys_register.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.states_arrays));
        spinner_states_register.setAdapter(adapter2);

        img_close_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intToLogin);
                getActivity().finish();
            }
        });

        img_psn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (psn_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_psn_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_off, getActivity().getTheme()));
                    } else {
                        img_psn_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_off));
                    }
                    ll_psn_register.setVisibility(View.GONE);
                    psn_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_psn_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_on, getActivity().getTheme()));
                    } else {
                        img_psn_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_psn_on));
                    }
                    ll_psn_register.setVisibility(View.VISIBLE);
                    psn_click = true;
                }
            }
        });

        img_live_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (live_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_live_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_off, getActivity().getTheme()));
                    } else {
                        img_live_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_off));
                    }
                    ll_live_register.setVisibility(View.GONE);
                    live_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_live_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_on, getActivity().getTheme()));
                    } else {
                        img_live_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_live_on));
                    }
                    ll_live_register.setVisibility(View.VISIBLE);
                    live_click = true;
                }
            }
        });

        img_steam_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (steam_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_steam_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_off, getActivity().getTheme()));
                    } else {
                        img_steam_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_off));
                    }
                    ll_steam_register.setVisibility(View.GONE);
                    steam_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_steam_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_on, getActivity().getTheme()));
                    } else {
                        img_steam_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_steam_on));
                    }
                    ll_steam_register.setVisibility(View.VISIBLE);
                    steam_click = true;
                }
            }
        });

        img_pes_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pes_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_pes_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_off, getActivity().getTheme()));
                    } else {
                        img_pes_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_off));
                    }
                    pes_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_pes_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_on, getActivity().getTheme()));
                    } else {
                        img_pes_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_pes_on));
                    }
                    pes_click = true;
                }
            }
        });

        img_fifa_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fifa_click) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_fifa_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_off, getActivity().getTheme()));
                    } else {
                        img_fifa_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_off));
                    }
                    fifa_click = false;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_fifa_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_on, getActivity().getTheme()));
                    } else {
                        img_fifa_register.setImageDrawable(getResources().getDrawable(R.drawable.ic_fifa_on));
                    }
                    fifa_click = true;
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidationFields.isEmpty(edt_password_register)) {
                    user = new User();
                    user.setName(edt_name_register.getText().toString());
                    user.setEmail(edt_email_register.getText().toString());
                    user.setCountry(spinner_countrys_register.getSelectedItem().toString());
                    user.setState(spinner_states_register.getSelectedItem().toString());

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
                    user.setId(edt_id_register.getText().toString());
                    user.setPsn(edt_psn_register.getText().toString());
                    user.setPassword(edt_password_register.getText().toString());

                    user.save();

                    mActionsListener.registerUser(getActivity(), user);
                } else {
                    edt_password_register.setError("Digite uma senha");
                }
            }
        });
    }

    @Override
    public void registerSuccess() {
        Intent intToMain = new Intent(getActivity(), MainActivity.class);
        startActivity(intToMain);
        getActivity().finish();
    }

    @Override
    public void registerError(Task task) {
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
        Snackbar snackBar = Snackbar.make(ll_activity_register, "Erro: " + exceptionError, android.support.design.widget.Snackbar.LENGTH_SHORT);
        snackBar.show();
    }
}
