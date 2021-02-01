package com.mytask.webservices.model;

public class Signup {

    private boolean status;
    private int statusCode;
    private String message;
    private SignupDataBean signup_data;

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

    public SignupDataBean getSignup_data() {
        return signup_data;
    }

    public void setSignup_data(SignupDataBean signup_data) {
        this.signup_data = signup_data;
    }

    public static class SignupDataBean {
        /**
         * fld_user_id : 13502
         * fld_user_email : cks.14sonu@gmail.com
         * fld_user_phone : 9598662796
         * fld_user_name : Satyendra
         * r_code : null
         */

        private String fld_user_id;
        private String fld_user_email;
        private String fld_user_phone;
        private String fld_user_name;
        private Object r_code;

        public String getFld_user_id() {
            return fld_user_id;
        }

        public void setFld_user_id(String fld_user_id) {
            this.fld_user_id = fld_user_id;
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

        public String getFld_user_name() {
            return fld_user_name;
        }

        public void setFld_user_name(String fld_user_name) {
            this.fld_user_name = fld_user_name;
        }

        public Object getR_code() {
            return r_code;
        }

        public void setR_code(Object r_code) {
            this.r_code = r_code;
        }
    }
}
