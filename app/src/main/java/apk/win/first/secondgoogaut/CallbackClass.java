package apk.win.first.secondgoogaut;

import java.util.List;

public class CallbackClass {

    interface Callback {

        List<Model> callingBack(int number) throws InterruptedException;
        void callingBackButton();
        void callingBackButtonFragment();
        void callingBackSecondFr(Model i) throws InterruptedException;
        void callingBackModelSherObjectToFragOne(ModelSharPref i);
        void callingFragment_goog();
        void callingFirstFragm();
    }

    public Callback callback;

    void sendNumberObject(Model i) throws InterruptedException {
        callback.callingBackSecondFr(i);
    }

    void sendModelSherObj(ModelSharPref i){
        callback.callingBackModelSherObjectToFragOne(i);
    }

    public void registerCallBack(Callback callback){
        this.callback = callback;
    }

    List<Model> loadNumberObject(int number) throws InterruptedException {
        return callback.callingBack(number);
    }

    public void loadFragment_goog(){
        callback.callingFragment_goog();
    }

    public void loadFirstFragm(){
        callback.callingFirstFragm();
    }

    void buttonBack() {
        callback.callingBackButton();
    }
    void buttonBackFrament(){
        callback.callingBackButtonFragment();
    }

}
