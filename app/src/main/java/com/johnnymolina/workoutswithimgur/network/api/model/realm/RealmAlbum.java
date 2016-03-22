package com.johnnymolina.workoutswithimgur.network.api.model.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Johnny on 3/21/2016.
 */
public class RealmAlbum extends RealmObject {

    private String id;
    private String title;
    private String description;
    private int datetime;
    private String cover;
    private String accountUrl;
    private int accountId;
    private String privacy;
    private String layout;
    private int views;
    private String link;
    private int imagesCount;
    private RealmList<RealmImage> images = new RealmList<RealmImage>();

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

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     *
     * @return
     *     The images
     */
    public RealmList<RealmImage> getImages() {
        return images;
    }

    /**
     *
     * @param images
     *     The images
     */
    public void setImages(RealmList<RealmImage> images) {
        this.images = images;
    }

}
