package apk.win.first.secondgoogaut;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


public class SecondFragment extends android.support.v4.app.Fragment {

    TextView name;
    TextView type;
    TextView description;
    TextView id;
    ImageView imageView;
    Button back;
    Model model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.second_fragment, container,false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            model = (Model) bundle.getSerializable("listModel");
        }

        name = rootView.findViewById(R.id.textView_name);
        type = rootView.findViewById(R.id.textView_type);
        description = rootView.findViewById(R.id.textView2_description);
        id = rootView.findViewById(R.id.textView_id);
        imageView = rootView.findViewById(R.id.imageView2);
        back = rootView.findViewById(R.id.button_back);

        name.setText(model.getName());
        id.setText(String.valueOf(model.getId()));
        description.setText(model.getDescription());
        type.setText(model.getType());
        Glide.with(rootView.getContext()).load(model.getIcon()).into(imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);

                callbacks.buttonBackFrament();
            }
        });

        return rootView.getRootView();
    }
}
