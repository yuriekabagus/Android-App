package storabot.suka.dev.projektest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import storabot.suka.dev.projektest.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    View custom;
    ImageView back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment Auth = HomeFragment.newInstance();
        SetFragment(Auth,false);
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        custom = getLayoutInflater().inflate(R.layout.custom_bar, null);
        //Set Your Custom Here

        toolbar.addView(custom);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    public void SetFragment(Fragment target, boolean backstack){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.konten_fragment, target);
        if(backstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
            Fragment currentFragment2 = getSupportFragmentManager().findFragmentById(R.id.konten_fragment);
            if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                getSupportFragmentManager().popBackStackImmediate(getSupportFragmentManager().
                        getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).
                        getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }else{
                finish();
            }


    }
    public void RefreshFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.konten_fragment);
        transaction.detach(current);
        transaction.attach(current);
        transaction.commit();
    }
}
