package apk.win.first.secondgoogaut.mvp;

import java.util.List;

import apk.win.first.secondgoogaut.Model;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Repositary mRepository;

    List<Model> listModel;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
    }

    @Override
    public void onButtonWasClicked(int number) throws InterruptedException {
        listModel = mRepository.loadModel(number);
        mView.giveModel(listModel);
    }
}
