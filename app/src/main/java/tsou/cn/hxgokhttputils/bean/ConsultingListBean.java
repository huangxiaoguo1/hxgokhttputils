package tsou.cn.hxgokhttputils.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/8 0008.
 * z咨询列表
 */

public class ConsultingListBean {

    /**
     * status : 0
     * msg : 获取成功
     * data : [{"id":"41","content":"偷税漏税200万左右，是怎么量刑的？有没有什么具体的说法","reply_count":"0","money":"38.00","type":"1","create_time":"前天 15:40","status":"1","name":"潘梦飞","avatar":"/Uploads/wap/avatar/2018-08-27/1535357180_15612162575b83b0fcf25f5.JPG","province":"江苏省","city":"无锡市","cate_name":"经济犯罪"},{"id":"40","content":"你好，请问下手机刷单交入职金，昨天进去，今天感觉不对劲！这个入职押金能退吗？还有我问他公司名称，营业执照，他都不敢说！那人就说，入职金退不了，要做可以接着教我！然后我现在也不知道该怎么办","reply_count":"2","money":"98.00","type":"1","create_time":"08月28日 14:41","status":"2","name":"潘梦飞","avatar":"/Uploads/wap/avatar/2018-08-27/1535357180_15612162575b83b0fcf25f5.JPG","province":"江苏省","city":"无锡市","cate_name":"一般民事"},{"id":"39","content":"受了工伤，8月2号申请劳动能力鉴定，8月13号结论才下来，请问一下，8月2号到8月13号之间如果你在家休息（有医生工伤病假条），请问这几天算不算停工留薪期。","reply_count":"0","money":"0.00","type":"0","create_time":"08月27日 15:10","status":"2","name":"潘梦飞","avatar":"/Uploads/wap/avatar/2018-08-27/1535357180_15612162575b83b0fcf25f5.JPG","province":"江苏省","city":"无锡市","cate_name":"人身损害"},{"id":"38","content":"房主只有购房合同能不能抵押出去啊？","reply_count":"0","money":"0.00","type":"0","create_time":"08月27日 15:10","status":"2","name":"潘梦飞","avatar":"/Uploads/wap/avatar/2018-08-27/1535357180_15612162575b83b0fcf25f5.JPG","province":"江苏省","city":"无锡市","cate_name":"抵押担保"},{"id":"37","content":"乡镇农机站，在农村合作社的时代，免费占用我们小队的土地10亩，现在确权，我们想收回属于我们自己小队的这10亩土地，我们通过怎样的方式，才能合理，合法的收回？","reply_count":"0","money":"0.00","type":"0","create_time":"08月27日 15:09","status":"1","name":"潘梦飞","avatar":"/Uploads/wap/avatar/2018-08-27/1535357180_15612162575b83b0fcf25f5.JPG","province":"江苏省","city":"无锡市","cate_name":"一般民事"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 41
         * content : 偷税漏税200万左右，是怎么量刑的？有没有什么具体的说法
         * reply_count : 0
         * money : 38.00
         * type : 1
         * create_time : 前天 15:40
         * status : 1
         * name : 潘梦飞
         * avatar : /Uploads/wap/avatar/2018-08-27/1535357180_15612162575b83b0fcf25f5.JPG
         * province : 江苏省
         * city : 无锡市
         * cate_name : 经济犯罪
         */

        private String id;
        private String content;
        private String reply_count;
        private String money;
        private String type;
        private String create_time;
        private String status;
        private String name;
        private String avatar;
        private String province;
        private String city;
        private String cate_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
