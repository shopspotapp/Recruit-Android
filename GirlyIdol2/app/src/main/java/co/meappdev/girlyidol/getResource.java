package co.meappdev.girlyidol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class getResource {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     *     The error
     */
    public Boolean getError() {
        return error;
    }

    /**
     *
     * @param error
     *     The error
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    /**
     *
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
