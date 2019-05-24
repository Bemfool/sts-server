package bgroup.stocktradingsystem.stsserver.domain.response;

import com.google.gson.Gson;

public class CustomResponse {
    private String resultJSON;
    private String objectJSON;
    private Gson json = new Gson();

    public CustomResponse(String resultJSON, String objectJSON) {
        this.resultJSON = resultJSON;
        this.objectJSON = objectJSON;
    }

    public CustomResponse(Result result, Object object) {
        this.resultJSON = json.toJson(result);
        this.objectJSON = json.toJson(object);
    }


    public CustomResponse(Result result) {
        this.resultJSON = json.toJson(result);
        this.objectJSON = null;
    }

    @Override
    public String toString() {
        return this.resultJSON + "\n" + objectJSON;
    }

    public String getResultJSON() {
        return resultJSON;
    }

    public void setResultJSON(Result result) {
        this.resultJSON = json.toJson(result);
    }

    public String getObjectJSON() {
        return objectJSON;
    }

    public void setObjectJSON(Object object) {
        this.objectJSON = json.toJson(object);
    }

}
