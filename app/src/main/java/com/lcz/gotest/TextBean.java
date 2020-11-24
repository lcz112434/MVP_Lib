package com.lcz.gotest;

import java.util.List;

/**
 * @author: Lczed
 * @date on 2020/11/24 16:07 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class TextBean {

    /**
     * code : 200
     * message : 成功!
     * result : [{"sid":"31575044","text":"以为是青铜\u2026\u2026\u2026\u2026","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2020/1015/388a5358-0eaf-11eb-a0f2-1866daeb0df0_wpd.jpg","video":"http://uvideo.spriteapp.cn/video/2020/1015/388a5358-0eaf-11eb-a0f2-1866daeb0df0_wpd.mp4","images":null,"up":"282","down":"4","forward":"2","comment":"38","uid":"23188275","name":"布丁","header":"http://wimg.spriteapp.cn/profile/large/2019/12/24/5e01929eac7e9_mini.jpg","top_comments_content":"叫什么名字？哪里买的？我要买给我闺女","top_comments_voiceuri":"","top_comments_uid":"23220040","top_comments_name":"用户nNGrdj","top_comments_header":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKuyMqNHMy5Aqws2wHFsxzqUnz5SzBWxqz6K4ntvXYfXdkZtbcUlA6ico8bqgdbicGhbwjt5JpgfVsA/132","passtime":"2020-11-12 18:51:33"},{"sid":"31574857","text":"我想碰个瓷需要注意什么","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2020/1014/1afc61f6-0df0-11eb-85bd-1866daeb0df0_wpd.jpg","video":"http://uvideo.spriteapp.cn/video/2020/1014/1afc61f6-0df0-11eb-85bd-1866daeb0df0_wpd.mp4","images":null,"up":"99","down":"6","forward":"0","comment":"34","uid":"23188364","name":"林深时见鹿","header":"http://wimg.spriteapp.cn/profile/large/2019/12/24/5e0192b3188d9_mini.jpg","top_comments_content":null,"top_comments_voiceuri":null,"top_comments_uid":null,"top_comments_name":null,"top_comments_header":null,"passtime":"2020-11-12 18:51:31"}]
     */

    private int code;
    private String message;
    /**
     * sid : 31575044
     * text : 以为是青铜…………
     * type : video
     * thumbnail : http://wimg.spriteapp.cn/picture/2020/1015/388a5358-0eaf-11eb-a0f2-1866daeb0df0_wpd.jpg
     * video : http://uvideo.spriteapp.cn/video/2020/1015/388a5358-0eaf-11eb-a0f2-1866daeb0df0_wpd.mp4
     * images : null
     * up : 282
     * down : 4
     * forward : 2
     * comment : 38
     * uid : 23188275
     * name : 布丁
     * header : http://wimg.spriteapp.cn/profile/large/2019/12/24/5e01929eac7e9_mini.jpg
     * top_comments_content : 叫什么名字？哪里买的？我要买给我闺女
     * top_comments_voiceuri :
     * top_comments_uid : 23220040
     * top_comments_name : 用户nNGrdj
     * top_comments_header : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKuyMqNHMy5Aqws2wHFsxzqUnz5SzBWxqz6K4ntvXYfXdkZtbcUlA6ico8bqgdbicGhbwjt5JpgfVsA/132
     * passtime : 2020-11-12 18:51:33
     */

    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String sid;
        private String text;
        private String type;
        private String thumbnail;
        private String video;
        private Object images;
        private String up;
        private String down;
        private String forward;
        private String comment;
        private String uid;
        private String name;
        private String header;
        private String top_comments_content;
        private String top_comments_voiceuri;
        private String top_comments_uid;
        private String top_comments_name;
        private String top_comments_header;
        private String passtime;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getTop_comments_content() {
            return top_comments_content;
        }

        public void setTop_comments_content(String top_comments_content) {
            this.top_comments_content = top_comments_content;
        }

        public String getTop_comments_voiceuri() {
            return top_comments_voiceuri;
        }

        public void setTop_comments_voiceuri(String top_comments_voiceuri) {
            this.top_comments_voiceuri = top_comments_voiceuri;
        }

        public String getTop_comments_uid() {
            return top_comments_uid;
        }

        public void setTop_comments_uid(String top_comments_uid) {
            this.top_comments_uid = top_comments_uid;
        }

        public String getTop_comments_name() {
            return top_comments_name;
        }

        public void setTop_comments_name(String top_comments_name) {
            this.top_comments_name = top_comments_name;
        }

        public String getTop_comments_header() {
            return top_comments_header;
        }

        public void setTop_comments_header(String top_comments_header) {
            this.top_comments_header = top_comments_header;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }
    }
}