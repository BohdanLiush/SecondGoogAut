package apk.win.first.secondgoogaut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    public final String URL = "https://api.guildwars2.com/v2/";
    public List<Model> listModel = new ArrayList<>();

    public ArrayList<Model> modelList = new ArrayList<>();

    public Thread loadObjectThread, p, getIdOneObjectThread;

    public String number = "";

    public int numberObj;
    public int count = 0;
    public Call<List<Model>> idsCall;

    public void loadNumberFromMain(int number) throws InterruptedException {
        //modelList.clear();  /** тут перше місце де ми поклали clear  */
        numberObj = number;
        count = 0;  /** нове дописали  */
        getIDSFromWeb();
    }

    public void getIDSFromWeb() throws InterruptedException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);
        while (modelList.size() < numberObj) {

            idsCall = idsApi.idsInfo(getIdsLoop(numberObj - modelList.size()));
            loadObjectThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        listModel = idsCall.execute().body();
                        if (listModel != null) {
                            modelList.addAll(listModel);
                        }
                        System.out.println("Smotrim: ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            loadObjectThread.start();
            loadObjectThread.join();
        }
    }

    public String getIdsLoop(int n) { // якшо різниця була 20 об.
        number = "";  // обнулення строки після 100 або іншого числа, тобто строка буде іти від 100, 101 як нам і треба
        for (int i = 0; i < n; i++) {
            number = number + count + ","; //101 do 120 // другий прохід
            count++;  //100
        }
        System.out.println("count " + count);
        return number;
    }
}
