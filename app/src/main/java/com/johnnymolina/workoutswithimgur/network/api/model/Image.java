
package com.johnnymolina.workoutswithimgur.network.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Image {

    @SerializedName("id") @Expose
    private String id;  //This will be our primary key using @PrimaryKey

    @SerializedName("title") @Expose
    private Object title;

    @SerializedName("description") @Expose
    private Object description;

    @SerializedName("datetime") @Expose
    private int datetime;

    @SerializedName("type") @Expose
    private String type;

    @SerializedName("animated") @Expose
    private boolean animated;

    @SerializedName("width") @Expose
    private int width;

    @SerializedName("height") @Expose
    private int height;

    @SerializedName("size") @Expose
    private int size;

    @SerializedName("views") @Expose
    private int views;

    @SerializedName("bandwidth") @Expose
    private int bandwidth;

    @SerializedName("link") @Expose
    private String link;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Image withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    public Object getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(Object title) {
        this.title = title;
    }

    public Image withTitle(Object title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Object getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Object description) {
        this.description = description;
    }

    public Image withDescription(Object description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The datetime
     */
    public int getDatetime() {
        return datetime;
    }

    /**
     * 
     * @param datetime
     *     The datetime
     */
    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public Image withDatetime(int datetime) {
        this.datetime = datetime;
        return this;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Image withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @return
     *     The animated
     */
    public boolean isAnimated() {
        return animated;
    }

    /**
     * 
     * @param animated
     *     The animated
     */
    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public Image withAnimated(boolean animated) {
        this.animated = animated;
        return this;
    }

    /**
     * 
     * @return
     *     The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public Image withWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * 
     * @return
     *     The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public Image withHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * 
     * @return
     *     The size
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(int size) {
        this.size = size;
    }

    public Image withSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * 
     * @return
     *     The views
     */
    public int getViews() {
        return views;
    }

    /**
     * 
     * @param views
     *     The views
     */
    public void setViews(int views) {
        this.views = views;
    }

    public Image withViews(int views) {
        this.views = views;
        return this;
    }

    /**
     * 
     * @return
     *     The bandwidth
     */
    public int getBandwidth() {
        return bandwidth;
    }

    /**
     * 
     * @param bandwidth
     *     The bandwidth
     */
    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Image withBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
        return this;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    public Image withLink(String link) {
        this.link = link;
        return this;
    }

}
