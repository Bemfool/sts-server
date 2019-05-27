import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import utils.*;

public class Main {

    public static void main(String[] args) throws IOException {
        AdminAccount adminAccount = new AdminAccount("8888888888", "000000");
        String json = new Gson().toJson(adminAccount);
        System.out.println(json);

//        CustomResp cr = new HttpCommon().doHttp("/admin/login", "POST", json);
//        System.out.println(cr.getResultJSON());
//        System.out.println(cr.getObjectJSON());
//        cr = new HttpCommon().doHttp("/admin/test", "GET", json);
//        System.out.println(cr.getResultJSON());
//        System.out.println(cr.getObjectJSON());

        /* 通过PathParameter传输参数(例如下面的1) */
        /* 返回一个List类型(例如下面的List<Integer>) */
        CustomResp cr = new HttpCommon().doHttp("/securities/fund_connected/1", "GET", json);
        System.out.println(cr.getResultJSON());
        System.out.println(cr.getObjectJSON());
        Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
        List<Integer> fundIds = new Gson().fromJson(cr.getObjectJSON(), listType);
        System.out.println(fundIds.get(0));
    }
}
