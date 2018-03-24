package Response;

import java.lang.ref.SoftReference;
import java.util.List;
import com.google.gson.Gson;

public class ResponseData {
    private String error;
    private String nextPage;
    private String[] nameErrors;

    public ResponseData(String error, String nextPage, String[] nameErrors){
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

    public String[] getNameErrors() {
        return nameErrors;
    }

    public void setNameErrors(String[] nameErrors) {
        this.nameErrors = nameErrors;
    }

    public String ToJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
