package com.mytask.webservices.model;

public class Login {
    
    private boolean status;
    private int statusCode;
    private String message;
    private LoginDataBean login_data;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginDataBean getLogin_data() {
        return login_data;
    }

    public void setLogin_data(LoginDataBean login_data) {
        this.login_data = login_data;
    }

    public static class LoginDataBean {
        /**
         * fld_user_id : 13502
         * fld_user_name : Satyendra
         * fld_l_name : null
         * fld_user_email : cks.14sonu@gmail.com
         * fld_user_phone : 9598662796
         * fld_user_address : null
         * fld_user_locality : null
         * fld_user_city : null
         * fld_user_state : null
         * fld_user_pincode : null
         * isOtpVerified : 0
         * r_code : xd7eJ13502
         * gender : null
         * dob : null
         * status : 1
         */

        private String fld_user_id;
        private String fld_user_name;
        private Object fld_l_name;
        private String fld_user_email;
        private String fld_user_phone;
        private Object fld_user_address;
        private Object fld_user_locality;
        private Object fld_user_city;
        private Object fld_user_state;
        private Object fld_user_pincode;
        private int isOtpVerified;
        private String r_code;
        private Object gender;
        private Object dob;
        private int status;

        public String getFld_user_id() {
            return fld_user_id;
        }

        public void setFld_user_id(String fld_user_id) {
            this.fld_user_id = fld_user_id;
        }

        public String getFld_user_name() {
            return fld_user_name;
        }

        public void setFld_user_name(String fld_user_name) {
            this.fld_user_name = fld_user_name;
        }

        public Object getFld_l_name() {
            return fld_l_name;
        }

        public void setFld_l_name(Object fld_l_name) {
            this.fld_l_name = fld_l_name;
        }

        public String getFld_user_email() {
            return fld_user_email;
        }

        public void setFld_user_email(String fld_user_email) {
            this.fld_user_email = fld_user_email;
        }

        public String getFld_user_phone() {
            return fld_user_phone;
        }

        public void setFld_user_phone(String fld_user_phone) {
            this.fld_user_phone = fld_user_phone;
        }

        public Object getFld_user_address() {
            return fld_user_address;
        }

        public void setFld_user_address(Object fld_user_address) {
            this.fld_user_address = fld_user_address;
        }

        public Object getFld_user_locality() {
            return fld_user_locality;
        }

        public void setFld_user_locality(Object fld_user_locality) {
            this.fld_user_locality = fld_user_locality;
        }

        public Object getFld_user_city() {
            return fld_user_city;
        }

        public void setFld_user_city(Object fld_user_city) {
            this.fld_user_city = fld_user_city;
        }

        public Object getFld_user_state() {
            return fld_user_state;
        }

        public void setFld_user_state(Object fld_user_state) {
            this.fld_user_state = fld_user_state;
        }

        public Object getFld_user_pincode() {
            return fld_user_pincode;
        }

        public void setFld_user_pincode(Object fld_user_pincode) {
            this.fld_user_pincode = fld_user_pincode;
        }

        public int getIsOtpVerified() {
            return isOtpVerified;
        }

        public void setIsOtpVerified(int isOtpVerified) {
            this.isOtpVerified = isOtpVerified;
        }

        public String getR_code() {
            return r_code;
        }

        public void setR_code(String r_code) {
            this.r_code = r_code;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
