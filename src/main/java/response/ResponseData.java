package response;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ResponseData {
    private String error;
    private String nextPage;
    private ArrayList<String> nameErrors;

    public ResponseData(String error, String nextPage, ArrayList<String> nameErrors){
        this.error = error;
        this.nextPage = nextPage;
        this.nameErrors = nameErrors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public ArrayList<String> getNameErrors() {
        return nameErrors;
    }

    public void setNameErrors(ArrayList<String> nameErrors) {
        this.nameErrors = nameErrors;
    }

    public String ToJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
