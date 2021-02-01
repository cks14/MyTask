package com.mytask.webservices.model;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {


    private boolean status;
    private int statusCode;
    private int fld_total_page;
    private String message;
    private int grid_list_view;
    private int next_page;
    private List<ProductDataBean> product_data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getFld_total_page() {
        return fld_total_page;
    }

    public void setFld_total_page(int fld_total_page) {
        this.fld_total_page = fld_total_page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getGrid_list_view() {
        return grid_list_view;
    }

    public void setGrid_list_view(int grid_list_view) {
        this.grid_list_view = grid_list_view;
    }

    public int getNext_page() {
        return next_page;
    }

    public void setNext_page(int next_page) {
        this.next_page = next_page;
    }

    public List<ProductDataBean> getProduct_data() {
        return product_data;
    }

    public void setProduct_data(List<ProductDataBean> product_data) {
        this.product_data = product_data;
    }

    public static class ProductDataBean {
        /**
         * id : 616
         * cat_id : 216
         * unisex_type : 0
         * color_id : 57
         * size_id : 110
         * extra_price : 0
         * name : Workout Half Sleeve T-shirt
         * price : 359
         * spcl_price : 259
         * default_image : https://www.phaukat.com/uploads/products/1599992159-776.jpg
         * gif_image : https://www.phaukat.com/uploads/products/https://phaukat.com/uploads/products/1599992264-636.jpg
         * fld_total_rating : 0
         * fld_rating_count : 0
         * fld_review_count : 0
         * isInWishlist : false
         */

        private int id;
        private int cat_id;
        private String unisex_type;
        private int color_id;
        private int size_id;
        private String extra_price;
        private String name;
        private String price;
        private String spcl_price;
        private String default_image;
        private String gif_image;
        private int fld_total_rating;
        private int fld_rating_count;
        private int fld_review_count;
        private boolean isInWishlist;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public String getUnisex_type() {
            return unisex_type;
        }

        public void setUnisex_type(String unisex_type) {
            this.unisex_type = unisex_type;
        }

        public int getColor_id() {
            return color_id;
        }

        public void setColor_id(int color_id) {
            this.color_id = color_id;
        }

        public int getSize_id() {
            return size_id;
        }

        public void setSize_id(int size_id) {
            this.size_id = size_id;
        }

        public String getExtra_price() {
            return extra_price;
        }

        public void setExtra_price(String extra_price) {
            this.extra_price = extra_price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpcl_price() {
            return spcl_price;
        }

        public void setSpcl_price(String spcl_price) {
            this.spcl_price = spcl_price;
        }

        public String getDefault_image() {
            return default_image;
        }

        public void setDefault_image(String default_image) {
            this.default_image = default_image;
        }

        public String getGif_image() {
            return gif_image;
        }

        public void setGif_image(String gif_image) {
            this.gif_image = gif_image;
        }

        public int getFld_total_rating() {
            return fld_total_rating;
        }

        public void setFld_total_rating(int fld_total_rating) {
            this.fld_total_rating = fld_total_rating;
        }

        public int getFld_rating_count() {
            return fld_rating_count;
        }

        public void setFld_rating_count(int fld_rating_count) {
            this.fld_rating_count = fld_rating_count;
        }

        public int getFld_review_count() {
            return fld_review_count;
        }

        public void setFld_review_count(int fld_review_count) {
            this.fld_review_count = fld_review_count;
        }

        public boolean isIsInWishlist() {
            return isInWishlist;
        }

        public void setIsInWishlist(boolean isInWishlist) {
            this.isInWishlist = isInWishlist;
        }
    }
}
