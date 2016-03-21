
package com.johnnymolina.workoutswithimgur.network.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("datetime")
    @Expose
    private int datetime;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("account_url")
    @Expose
    private String accountUrl;
    @SerializedName("account_id")
    @Expose
    private int accountId;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("layout")
    @Expose
    private String layout;
    @SerializedName("views")
    @Expose
    private int views;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("images_count")
    @Expose
    private int imagesCount;
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();

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

    public Data withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Data withTitle(String title) {
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

    public Data withDescription(Object description) {
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

    public Data withDatetime(int datetime) {
        this.datetime = datetime;
        return this;
    }

    /**
     * 
     * @return
     *     The cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * 
     * @param cover
     *     The cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    public Data withCover(String cover) {
        this.cover = cover;
        return this;
    }

    /**
     * 
     * @return
     *     The accountUrl
     */
    public String getAccountUrl() {
        return accountUrl;
    }

    /**
     * 
     * @param accountUrl
     *     The account_url
     */
    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Data withAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * 
     * @param accountId
     *     The account_id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Data withAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * 
     * @return
     *     The privacy
     */
    public String getPrivacy() {
        return privacy;
    }

    /**
     * 
     * @param privacy
     *     The privacy
     */
    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Data withPrivacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    /**
     * 
     * @return
     *     The layout
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 
     * @param layout
     *     The layout
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Data withLayout(String layout) {
        this.layout = layout;
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

    public Data withViews(int views) {
        this.views = views;
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

    public Data withLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * 
     * @return
     *     The imagesCount
     */
    public int getImagesCount() {
        return imagesCount;
    }

    /**
     * 
     * @param imagesCount
     *     The images_count
     */
    public void setImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
    }

    public Data withImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
        return this;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Data withImages(List<Image> images) {
        this.images = images;
        return this;
    }

}
