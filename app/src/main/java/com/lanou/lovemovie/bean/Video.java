package com.lanou.lovemovie.bean;

import java.util.List;

/**
 * Created by dllo on 15/9/10.
 */
public class Video {


    /**
     * totalPageCount : 3
     * totalCount : 46
     * videoList : [{"id":54472,"url":"http://vfx.mtime.cn/Video/2015/06/04/mp4/150604164108319901_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/06/04/mp4/150604164108319901.mp4","image":"http://img31.mtime.cn/mg/2015/06/04/163929.61664672_235X132X4.jpg","title":"碟中谍5：神秘国度 中文版终极预告片","type":0,"length":139},{"id":56213,"url":"http://vfx.mtime.cn/Video/2015/09/06/mp4/150906153407855980.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/09/06/mp4/150906153407855980.mp4","image":"http://img31.mtime.cn/mg/2015/09/06/153228.74796700_235X132X4.jpg","title":"碟中谍5：神秘国度 中文版IMAX预告片","type":0,"length":156},{"id":55572,"url":"http://vfx.mtime.cn/Video/2015/08/03/mp4/150803150411725990_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/08/03/mp4/150803150411725990.mp4","image":"http://img31.mtime.cn/mg/2015/08/03/150350.35990360_235X132X4.jpg","title":"碟中谍5：神秘国度 中国版预告片","type":0,"length":30},{"id":53317,"url":"http://vfx.mtime.cn/Video/2015/03/24/mp4/150324090518651159_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/03/24/mp4/150324090518651159.mp4","image":"http://img31.mtime.cn/mg/2015/03/24/085836.68614015_235X132X4.jpg","title":"碟中谍5：神秘国度 中文版预告片","type":0,"length":139},{"id":54788,"url":"http://vfx.mtime.cn/Video/2015/06/23/mp4/150623111204478693_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/06/23/mp4/150623111204478693.mp4","image":"http://img31.mtime.cn/mg/2015/06/23/093221.41979808_235X132X4.jpg","title":"碟中谍5：神秘国度 台湾版终极预告片","type":0,"length":161},{"id":55020,"url":"http://vfx.mtime.cn/Video/2015/07/07/mp4/150707101932773710_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/07/mp4/150707101932773710.mp4","image":"http://img31.mtime.cn/mg/2015/07/07/101849.89301809_235X132X4.jpg","title":"碟中谍5：神秘国度 英国版预告片","type":0,"length":30},{"id":53315,"url":"http://vfx.mtime.cn/Video/2015/03/23/mp4/150323234818834056_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/03/23/mp4/150323234818834056.mp4","image":"http://img31.mtime.cn/mg/2015/03/23/234810.50406282_235X132X4.jpg","title":"碟中谍5：神秘国度 预告片1","type":0,"length":161},{"id":54470,"url":"http://vfx.mtime.cn/Video/2015/06/04/mp4/150604160959286151_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/06/04/mp4/150604160959286151.mp4","image":"http://img31.mtime.cn/mg/2015/06/04/160948.56217560_235X132X4.jpg","title":"碟中谍5：神秘国度 预告片2","type":0,"length":155},{"id":54786,"url":"http://vfx.mtime.cn/Video/2015/06/23/mp4/150623074054132676_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/06/23/mp4/150623074054132676.mp4","image":"http://img31.mtime.cn/mg/2015/06/23/074000.69440797_235X132X4.jpg","title":"碟中谍5：神秘国度 预告片3","type":0,"length":150},{"id":53302,"url":"http://vfx.mtime.cn/Video/2015/03/23/mp4/150323080506353750_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/03/23/mp4/150323080506353750.mp4","image":"http://img31.mtime.cn/mg/2015/03/23/080345.56467094_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片1","type":0,"length":64},{"id":55313,"url":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722093521314297_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722093521314297.mp4","image":"http://img31.mtime.cn/mg/2015/07/22/093516.39320582_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片2","type":0,"length":29},{"id":55314,"url":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722093831754862_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722093831754862.mp4","image":"http://img31.mtime.cn/mg/2015/07/22/093831.86831389_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片3","type":0,"length":29},{"id":55315,"url":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094052174088_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094052174088.mp4","image":"http://img31.mtime.cn/mg/2015/07/22/094016.32819209_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片\"Too Far\"","type":0,"length":15},{"id":55316,"url":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094238024851_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094238024851.mp4","image":"http://img31.mtime.cn/mg/2015/07/22/094241.59725336_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片\"Ripcord\"","type":0,"length":30},{"id":55317,"url":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094543745414_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094543745414.mp4","image":"http://img31.mtime.cn/mg/2015/07/22/094541.27322532_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片\"Drive\"","type":0,"length":30},{"id":55318,"url":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094853669841_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/22/mp4/150722094853669841.mp4","image":"http://img31.mtime.cn/mg/2015/07/22/094858.68739951_235X132X4.jpg","title":"碟中谍5：神秘国度 电视宣传片\"Superpower\"","type":0,"length":30},{"id":55970,"url":"http://vfx.mtime.cn/Video/2015/08/20/mp4/150820151542517366.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/08/20/mp4/150820151542517366.mp4","image":"http://img31.mtime.cn/mg/2015/08/20/151440.80157376_235X132X4.jpg","title":"碟中谍5：神秘国度 病毒视频之碟舞","type":0,"length":128},{"id":56065,"url":"http://vfx.mtime.cn/Video/2015/08/27/mp4/150827174041283659.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/08/27/mp4/150827174041283659.mp4","image":"http://img31.mtime.cn/mg/2015/08/27/174016.18678117_235X132X4.jpg","title":"碟中谍5：神秘国度 中文版电视宣传片1","type":0,"length":30},{"id":56066,"url":"http://vfx.mtime.cn/Video/2015/08/27/mp4/150827174324088058.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/08/27/mp4/150827174324088058.mp4","image":"http://img31.mtime.cn/mg/2015/08/27/174246.62535185_235X132X4.jpg","title":"碟中谍5：神秘国度 中文版电视宣传片2","type":0,"length":29},{"id":56171,"url":"http://vfx.mtime.cn/Video/2015/09/01/mp4/150901145741237439.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/09/01/mp4/150901145741237439.mp4","image":"http://img31.mtime.cn/mg/2015/09/01/145635.34372926_235X132X4.jpg","title":"碟中谍5：神秘国度 IMAX特制倒计时片头","type":0,"length":54}]
     */

    private int totalPageCount;
    private int totalCount;
    private List<VideoListEntity> videoList;

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setVideoList(List<VideoListEntity> videoList) {
        this.videoList = videoList;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<VideoListEntity> getVideoList() {
        return videoList;
    }

    public static class VideoListEntity {
        /**
         * id : 54472
         * url : http://vfx.mtime.cn/Video/2015/06/04/mp4/150604164108319901_480.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2015/06/04/mp4/150604164108319901.mp4
         * image : http://img31.mtime.cn/mg/2015/06/04/163929.61664672_235X132X4.jpg
         * title : 碟中谍5：神秘国度 中文版终极预告片
         * type : 0
         * length : 139
         */

        private int id;
        private String url;
        private String hightUrl;
        private String image;
        private String title;
        private int type;
        private int length;

        public void setId(int id) {
            this.id = id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public String getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public int getLength() {
            return length;
        }
    }
}
