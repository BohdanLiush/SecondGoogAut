package apk.win.first.secondgoogaut.mvp;

import java.util.List;

import apk.win.first.secondgoogaut.Model;
import apk.win.first.secondgoogaut.NetworkManager;

public class MainRepository implements MainContract.Repositary {

    public NetworkManager networkManager = new NetworkManager();

    @Override
    public List<Model> loadModel(int number) throws InterruptedException {
        networkManager.loadNumberFromMain(number);
        return networkManager.modelList;
    }
}
