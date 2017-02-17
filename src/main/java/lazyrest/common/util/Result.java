package lazyrest.common.util;


/**
 * Created by Melon on 17/2/15.
 */
public class Result {

    private Status status;
    private Object data;

    public Result success() {
        this.status = new Status(true);
        return this;
    }

    public Result success(Object data) {
        this.status = new Status(true);
        this.data = data;
        return this;
    }

    public Result failure() {
        this.status = new Status(false);
        return this;
    }

    public Result failure(int code, String message) {
        this.status = new Status(code, message);
        return this;
    }

    public Result failure(String message) {
        this.status = new Status(Constants.CODE_BAD_REQUEST, message);
        return this;
    }

    public Result failure(String message, Object data) {
        this.status = new Status(Constants.CODE_BAD_REQUEST, message);
        this.data = data;
        return this;
    }

    public Result failure(int code, String message, Object data) {
        this.status = new Status(code, message);
        this.data = data;
        return this;
    }


    public Status getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public class Status {

        private int code;
        private String message;

        public Status(boolean success) {
            if (success) {
                code = Constants.CODE_SUCCESS;
            } else {
                code = Constants.CODE_BAD_REQUEST;
            }
        }

        public Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
