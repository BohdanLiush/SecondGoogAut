package apk.win.first.secondgoogaut;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import apk.win.first.secondgoogaut.mvp.MainContract;
import apk.win.first.secondgoogaut.mvp.MainPresenter;

public class FirstFragment extends Fragment implements Serializable, MainContract.View{

    public int numberObj = 50;
    public List<Model> model;
    public int globalPosition = 0;

    public ArrayList<Model> modelList = new ArrayList<>();
    AdapterNew adapterNew;
    RecyclerView recyclerView;
    ModelSharPref modelSharPref = new ModelSharPref();
    View rootView;
    private MainContract.Presenter mPresenter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_one, container,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = rootView.findViewById(R.id.recyclerlist23);
        recyclerView.setLayoutManager(layoutManager);
        mPresenter = new MainPresenter(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            modelSharPref = (ModelSharPref) bundle.getSerializable("listModelSherPr");
            System.out.println("Hello");
        }

        MainActivity activityHome = (MainActivity) container.getContext();
        CallbackClass callbacks = new CallbackClass();
        callbacks.registerCallBack(activityHome);

        try {
            /** старий участок кода до презентера*/
            //model = callbacks.loadNumberObject(numberObj);
            /**-----------------------------*/
            /** через презентер */
            mPresenter.onButtonWasClicked(numberObj);
            /**-----------------------------*/
            //modelList.addAll(model);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapterNew = new AdapterNew(model);
        recyclerView.setAdapter(adapterNew);
        adapterNew.notifyDataSetChanged();

        if (globalPosition!= 0) {
            /*MainActivity activityHome0 = (MainActivity) rootView.getContext();
            CallbackClass callbacks = new CallbackClass();*/
            callbacks.registerCallBack(activityHome);
            try {
                callbacks.sendNumberObject(model.get(globalPosition));
                globalPosition=0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(container.getContext(),
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            globalPosition = position;
                            if (modelSharPref.getEmailSher() ==""){
                                MainActivity activityHome = (MainActivity) view.getContext();
                                CallbackClass callbacks = new CallbackClass();
                                callbacks.registerCallBack(activityHome);
                                callbacks.loadFragment_goog();
                            } else {
                                MainActivity activityHome = (MainActivity) view.getContext();
                                CallbackClass callbacks = new CallbackClass();
                                callbacks.registerCallBack(activityHome);
                                try {
                                    callbacks.sendNumberObject(model.get(position));
                                    globalPosition=0;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }));

        return rootView.getRootView();
    }

    @Override
    public void giveModel(List<Model> models) {
        model = models;
    }
}
