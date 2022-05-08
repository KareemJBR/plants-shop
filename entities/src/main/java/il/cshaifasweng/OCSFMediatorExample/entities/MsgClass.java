package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class MsgClass implements Serializable {
    private static final long serialVersionUID = -8224097662914849956L;
    private String msg;
    Object obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public MsgClass() {
    }

    public MsgClass(String msg) {
        this.msg = msg;
        this.obj=null;
    }

    public MsgClass(String msg, Object obj) {
        this.msg = msg;
        this.obj = obj;
    }
}
