
package com.johnnymolina.workoutswithimgur.network.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("data")
    @Expose
    private Album album;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("status")
    @Expose
    private int status;

    /**
     * 
     * @return
     *     The album
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * 
     * @param album
     *     The album
     */
    public void setAlbum(Album album) {
        this.album = album;
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

}
