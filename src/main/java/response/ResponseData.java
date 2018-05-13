package response;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
    private String error;
    private String nextPage;
    private List<String> nameErrors;

    public ResponseData(String error, String nextPage, List<String> nameErrors){
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

    public List<String> getNameErrors() {
        return nameErrors;
    }

    public void setNameErrors(List<String> nameErrors) {
        this.nameErrors = nameErrors;
    }

    public boolean isError(){
        return nameErrors != null && !nameErrors.isEmpty();
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
