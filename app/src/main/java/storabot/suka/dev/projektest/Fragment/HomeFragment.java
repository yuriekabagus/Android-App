package storabot.suka.dev.projektest.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import storabot.suka.dev.projektest.API.ApiRequest;
import storabot.suka.dev.projektest.API.User;
import storabot.suka.dev.projektest.R;

public class HomeFragment extends Fragment {
    ArrayList<User> USers = new ArrayList<User>();
    RecyclerView rcy_user;
    ProgressDialog storeDialog;
    private LinearLayoutManager VerticalLayoutManager;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
    ;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rcy_user = (RecyclerView)v.findViewById(R.id.rcy_);
        VerticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcy_user.setLayoutManager(VerticalLayoutManager);
        storeDialog = new ProgressDialog(getContext());
        storeDialog.setMessage("Processing Data ...");
        storeDialog.show();
      function_getDATA();


      return v;
    }
    public void function_getDATA(){
        Map<String, String> data = new HashMap<String, String>();
        String url = "https://gorest.co.in/public-api/users?_format=json&access-token=ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ";
        ApiRequest req = new ApiRequest(url, data, getContext(),"GET") {
            @Override
            public String onResponseComplete(String response) {

                try {
                    storeDialog.dismiss();
                    JSONObject resRedeem= null;
                     resRedeem = new JSONObject(response);
                    JSONObject respMeta = new JSONObject(resRedeem.getString("_meta"));

                    if(respMeta.getString("code").equals("200")){
                        JSONArray arrlist = new JSONArray(resRedeem.getString("result"));
                        for (int i = 0; i < arrlist.length(); i++) {

                            JSONObject objContacts = arrlist.getJSONObject(i);
                            USers.add(new User(objContacts.getString("id"),
                                    objContacts.getString("name"),
                                    objContacts.getString("email"),
                                    objContacts.getString("gender"),
                                    objContacts.getString("phone")
                            ));

                        }
                        DataAdapters adapters = new DataAdapters(getContext(),USers);
                        rcy_user.setAdapter(adapters);
                        rcy_user.invalidate();
                    }else{
                        Toast.makeText(getContext(),respMeta.getString("message"),Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onRequestTimeOut(String errMessage) {
                storeDialog.dismiss();
                Toast.makeText(getContext(), errMessage, Toast.LENGTH_LONG).show();
            }
        };
    }
    class DataAdapters extends RecyclerView.Adapter<DataAdapters.ViewHolder> {
        private ArrayList<User> android_versions;
        private Context context;

        public DataAdapters(Context applicationContext, ArrayList<User> android_versions) {

            this.android_versions = android_versions;

        }


        @Override
        public DataAdapters.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_profile, viewGroup, false);
            return new DataAdapters.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapters.ViewHolder viewHolder, final int i) {

            viewHolder.tv_name.setText(android_versions.get(i).getUsername());
            viewHolder.tv_email.setText(android_versions.get(i).getEmail());
            viewHolder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomDialog(android_versions.get(i));
                }
            });


            //Picasso.with(context).load(android_versions.get(i).getUrlImage()).fit().into(viewHolder.img_android);
        }

        @Override
        public int getItemCount() {
            return android_versions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            RelativeLayout container;
            TextView tv_name,tv_email;
            LinearLayout parent ;

            public ViewHolder(View view) {
                super(view);
                tv_email = (TextView) view.findViewById(R.id.email);
                tv_name= (TextView)view.findViewById(R.id.name);
                parent = (LinearLayout)view.findViewById(R.id.lyt_parent);

            }


        }
    }
    private void showCustomDialog(User p) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_detail);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.title)).setText(p.getUsername());
        ((TextView) dialog.findViewById(R.id.email)).setText(p.getEmail());
        ((TextView) dialog.findViewById(R.id.gender)).setText(p.getGender());
        ((TextView) dialog.findViewById(R.id.phone)).setText(p.getPhone());


        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
