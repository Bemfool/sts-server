import com.google.gson.Gson;

import java.io.IOException;
import utils.*;

public class Main {

    public static void main(String[] args) throws IOException {
        AdminAccount adminAccount = new AdminAccount("8888888888", "000000");
        String json = new Gson().toJson(adminAccount);
        System.out.println(json);

        CustomResp cr = new HttpCommon().doHttp("/admin/login", "POST", json);
        System.out.println(cr.getResultJSON());
        System.out.println(cr.getObjectJSON());
        cr = new HttpCommon().doHttp("/admin/test", "GET", json);
        System.out.println(cr.getResultJSON());
        System.out.println(cr.getObjectJSON());
    }
}
