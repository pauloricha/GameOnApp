package com.gameondigital.gameonapp.MyProfile;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.gameondigital.gameonapp.ListPlayers.PlayersFragment;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.MyProfile.CreditsMyProfile.CreditsMyProfileFragment;
import com.gameondigital.gameonapp.MyProfile.DataMyProfile.DataMyProfileFragment;
import com.gameondigital.gameonapp.MyProfile.StaticsMyProfile.StaticsMyProfileFragment;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MyProfileFragment extends Fragment implements MyProfileContract.View{

    private MyProfileContract.UserActionsListener mActionsListener;

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int STORAGE_PERMISSION_CODE = 23;

    private ImageView img_top_my_profile;
    private TextView txt_id_top_my_profile,
            txt_name_top_my_profile;
    private ViewPager vp_my_profile;
    private TabLayout tb_my_profile;

    private ProgressDialog mProgress;
    private StorageReference mStorageReference;

    private String[] dataPlayer;

    private View rootView;

    public static PlayersFragment newInstance() {
        Log.i("LOOG", "TournamentsActivity -> newInstance");
        return new PlayersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsActivity -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsActivity -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        initViews();
        initListeners();
        initObjects();

        getActivity().setTitle("Meu Perfil");

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "TournamentsActivity -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "MyProfileFragment -> initViews");
        mProgress = new ProgressDialog(getActivity());

        img_top_my_profile = rootView.findViewById(R.id.img_top_my_profile);
        txt_id_top_my_profile = rootView.findViewById(R.id.txt_id_top_my_profile);
        txt_name_top_my_profile = rootView.findViewById(R.id.txt_name_top_my_profile);
        vp_my_profile = rootView.findViewById(R.id.vp_my_profile);
        tb_my_profile = rootView.findViewById(R.id.tb_my_profile);

        mActionsListener = new MyProfilePresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "MyProfileFragment -> initListeners");
        img_top_my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.CAMERA)) {
                        onLaunchCamera();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA},
                                CAMERA_REQUEST_CODE);
                    }
                }
                else {
                    onLaunchCamera();
                }
            }
        });

        ViewTreeObserver viewTreeObserver = vp_my_profile.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = rootView.findViewById(R.id.ll_my_profile);
                        int viewPagerWidth = vp_my_profile.getWidth();
                        int viewPagerHeight = linearLayout.getHeight();

                        layoutParams.width = viewPagerWidth;
                        layoutParams.height = viewPagerHeight - tb_my_profile.getHeight();

                        Log.i("LOOG", "Width -> " + String.valueOf(viewPagerWidth));
                        Log.i("LOOG", "Height -> " + String.valueOf(viewPagerHeight));

                        vp_my_profile.setLayoutParams(layoutParams);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            vp_my_profile.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
    }

    private void initObjects(){
        Log.i("LOOG", "MyProfileFragment -> initObjects");
        setupViewPager(vp_my_profile);
        tb_my_profile.setupWithViewPager(vp_my_profile);

        setImageView();

        mActionsListener.getDataMyProfile();
    }

    private void setupViewPager(final ViewPager viewPager) {
        Log.i("LOOG", "MyProfileFragment -> setupViewPager");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new StaticsMyProfileFragment() , "Estatísticas");
        adapter.addFragment(new DataMyProfileFragment(), "Meus Dados");
        adapter.addFragment(new CreditsMyProfileFragment(), "Créditos");
        viewPager.setAdapter(adapter);
    }

    private void setImageView() {
        Log.i("LOOG", "MyProfileFragment -> setImageView");

        String filePath = Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getActivity().getPackageName()
                + "/Files/Photo.png";

        File file = new File(filePath);
        if(file.exists()){
            Bitmap bmp = BitmapFactory.decodeFile(filePath);
            img_top_my_profile.setImageBitmap(bmp);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            mProgress.setMessage("Carregando imagem...");
            mProgress.show();

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] databaos = baos.toByteArray();

            img_top_my_profile.setImageBitmap(bitmap);

            storeImage(bitmap);

            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imagesRef = storageRef.child("players/" + FirebaseConfiguration.getFirebaseAuth().getCurrentUser().getEmail() + "/Photo"/* + new Date().getTime()*/);

            UploadTask uploadTask = imagesRef.putBytes(databaos);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.i("LOOG", exception.toString());
                    Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();
                }
            } );
        }
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("LOOG",
                    "Error creating media file, check storage permissions: ");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("LOOG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("LOOG", "Error accessing file: " + e.getMessage());
        }
    }

    private File getOutputMediaFile(){
        if(isReadStorageAllowed()){
            Toast.makeText(getActivity(),"You already have the permission",Toast.LENGTH_LONG).show();
        }

        requestStoragePermission();
        File sourcePath = Environment.getExternalStorageDirectory();

        File path = new File(sourcePath + "/" + "/Android/data/"
                + getActivity().getPackageName()
                + "/Files" + "/");

        path.mkdir();

        File mediaFile;
        String mImageName = "Photo.png";
        mediaFile = new File(path.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private boolean isReadStorageAllowed() {
        int result = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }

    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void setDataMyProfile(User user) {
        Log.i("LOOG", "MyProfileFragment -> setDataProfilePlayer");
        mStorageReference = FirebaseStorage.getInstance().getReference();

        /*Glide.with(getActivity())
                .using(new FirebaseImageLoader())
                .load(mStorageReference.child("players/" + user.getEmail() + "/Photo"))
                .into(img_top_my_profile);*/

        txt_id_top_my_profile.setText(user.getPsn());
        txt_name_top_my_profile.setText(user.getName());
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> myProfileFragmentList = new ArrayList<>();
        private final List<String> myProfileFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return myProfileFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return myProfileFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return myProfileFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            Log.i("LOOG", "MyProfileFragment -> addFragment");

            myProfileFragmentList.add(fragment);
            myProfileFragmentTitleList.add(title);
        }
    }
}
