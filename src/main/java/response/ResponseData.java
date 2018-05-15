package response;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
    private String error;
    private String nextPage;

    public ResponseData(String error, String nextPage){
        this.error = error;
        this.nextPage = nextPage;
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

    public boolean isError(){
        return error != null && !error.isEmpty();
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
