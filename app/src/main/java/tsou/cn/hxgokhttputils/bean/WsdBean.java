package tsou.cn.hxgokhttputils.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/20 0020.
 */

public class WsdBean {

    /**
     * status : 1
     * showMessage : 操作成功
     * errMsg : null
     * data : [{"dvalue":"幼儿班小班","dflag":"wsd.grade.type","dkey":"1"},{"dvalue":"幼儿班中班","dflag":"wsd.grade.type","dkey":"2"},{"dvalue":"幼儿班大班","dflag":"wsd.grade.type","dkey":"3"},{"dvalue":"一年级","dflag":"wsd.grade.type","dkey":"11"},{"dvalue":"二年级","dflag":"wsd.grade.type","dkey":"12"},{"dvalue":"三年级","dflag":"wsd.grade.type","dkey":"13"},{"dvalue":"四年级","dflag":"wsd.grade.type","dkey":"14"},{"dvalue":"五年级","dflag":"wsd.grade.type","dkey":"15"},{"dvalue":"六年级","dflag":"wsd.grade.type","dkey":"16"},{"dvalue":"初一","dflag":"wsd.grade.type","dkey":"21"},{"dvalue":"初二","dflag":"wsd.grade.type","dkey":"22"},{"dvalue":"初三","dflag":"wsd.grade.type","dkey":"23"},{"dvalue":"高一","dflag":"wsd.grade.type","dkey":"31"},{"dvalue":"高二","dflag":"wsd.grade.type","dkey":"32"},{"dvalue":"高三","dflag":"wsd.grade.type","dkey":"33"},{"dvalue":"成人","dflag":"wsd.grade.type","dkey":"41"}]
     */

    private int status;
    private String showMessage;
    private String errMsg;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "WsdBean{" +
                "status=" + status +
                ", showMessage='" + showMessage + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dvalue : 幼儿班小班
         * dflag : wsd.grade.type
         * dkey : 1
         */

        private String dvalue;
        private String dflag;
        private String dkey;

        @Override
        public String toString() {
            return "DataBean{" +
                    "dvalue='" + dvalue + '\'' +
                    ", dflag='" + dflag + '\'' +
                    ", dkey='" + dkey + '\'' +
                    '}';
        }

        public String getDvalue() {
            return dvalue;
        }

        public void setDvalue(String dvalue) {
            this.dvalue = dvalue;
        }

        public String getDflag() {
            return dflag;
        }

        public void setDflag(String dflag) {
            this.dflag = dflag;
        }

        public String getDkey() {
            return dkey;
        }

        public void setDkey(String dkey) {
            this.dkey = dkey;
        }
    }
}
