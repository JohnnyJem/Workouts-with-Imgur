
package com.johnnymolina.workoutswithimgur.network.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Albums {

    @SerializedName("data") @Expose
    private Data data;

    @SerializedName("success") @Expose
    private boolean success;

    @SerializedName("status") @Expose
    private int status;

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    public Albums withData(Data data) {
        this.data = data;
        return this;
    }

    /**
     * 
     * @return
     *     The success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Albums withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 
     * @return
     *     The status
     */
    public int getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public Albums withStatus(int status) {
        this.status = status;
        return this;
    }

}
