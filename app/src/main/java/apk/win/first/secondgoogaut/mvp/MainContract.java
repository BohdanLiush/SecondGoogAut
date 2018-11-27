package apk.win.first.secondgoogaut.mvp;

import java.util.List;

import apk.win.first.secondgoogaut.Model;

public interface MainContract {

    interface View{
        void giveModel(List<Model> models);
    }

    interface Presenter{
        void onButtonWasClicked(int number) throws InterruptedException;
    }

    interface Repositary{
        List<Model> loadModel(int number) throws InterruptedException;
    }


}
