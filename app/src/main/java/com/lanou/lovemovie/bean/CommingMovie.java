package com.lanou.lovemovie.bean;

import java.util.List;

/**
 * Created by dllo on 15/9/12.
 */
public class CommingMovie {


    /**
     * attention : [{"id":193881,"title":"小黄人大眼萌","image":"http://img31.mtime.cn/mt/2015/09/10/094704.35271733_1280X720X2.jpg","releaseDate":"9月13日上映","rYear":2015,"rMonth":9,"rDay":13,"type":"动画 | 喜剧 | 家庭","director":"凯尔·巴尔达","actor1":"桑德拉·布洛克","a
     **/

    private List<AttentionEntity> attention;
    private List<MoviecomingsEntity> moviecomings;

    public void setAttention(List<AttentionEntity> attention) {
        this.attention = attention;
    }

    public void setMoviecomings(List<MoviecomingsEntity> moviecomings) {
        this.moviecomings = moviecomings;
    }

    public List<AttentionEntity> getAttention() {
        return attention;
    }

    public List<MoviecomingsEntity> getMoviecomings() {
        return moviecomings;
    }

    public static class AttentionEntity {
        /**
         * id : 193881
         * title : 小黄人大眼萌
         * image : http://img31.mtime.cn/mt/2015/09/10/094704.35271733_1280X720X2.jpg
         * releaseDate : 9月13日上映
         * rYear : 2015
         * rMonth : 9
         * rDay : 13
         * type : 动画 | 喜剧 | 家庭
         * director : 凯尔·巴尔达
         * actor1 : 桑德拉·布洛克
         * actor2 : 乔·哈姆
         * locationName : 美国
         * isTicket : true
         * wantedCount : 8243
         * videoCount : 3
         * videos : [{"videoId":55397,"url":"http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714_480.mp4","hightUrl":"http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714.mp4","image":"http://img31.mtime.
         * isVideo : true
         */

        private int id;
        private String title;
        private String image;
        private String releaseDate;
        private int rYear;
        private int rMonth;
        private int rDay;
        private String type;
        private String director;
        private String actor1;
        private String actor2;
        private String locationName;
        private boolean isTicket;
        private int wantedCount;
        private int videoCount;
        private boolean isVideo;
        private List<VideosEntity> videos;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public void setIsTicket(boolean isTicket) {
            this.isTicket = isTicket;
        }

        public void setWantedCount(int wantedCount) {
            this.wantedCount = wantedCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public void setIsVideo(boolean isVideo) {
            this.isVideo = isVideo;
        }

        public void setVideos(List<VideosEntity> videos) {
            this.videos = videos;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int getRYear() {
            return rYear;
        }

        public int getRMonth() {
            return rMonth;
        }

        public int getRDay() {
            return rDay;
        }

        public String getType() {
            return type;
        }

        public String getDirector() {
            return director;
        }

        public String getActor1() {
            return actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public String getLocationName() {
            return locationName;
        }

        public boolean getIsTicket() {
            return isTicket;
        }

        public int getWantedCount() {
            return wantedCount;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public boolean getIsVideo() {
            return isVideo;
        }

        public List<VideosEntity> getVideos() {
            return videos;
        }

        public static class VideosEntity {
            /**
             * videoId : 55397
             * url : http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714_480.mp4
             * hightUrl : http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714.mp4
             * image : http://img31.mtime.cn/mg/2015/07/24/095123.38814335.jpg
             * length : 29
             * title : 小黄人大眼萌 中国版预告片
             */

            private int videoId;
            private String url;
            private String hightUrl;
            private String image;
            private int length;
            private String title;

            public void setVideoId(int videoId) {
                this.videoId = videoId;
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

            public void setLength(int length) {
                this.length = length;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getVideoId() {
                return videoId;
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

            public int getLength() {
                return length;
            }

            public String getTitle() {
                return title;
            }
        }
    }

    public static class MoviecomingsEntity {
        /**
         * id : 193881
         * title : 小黄人大眼萌
         * image : http://img31.mtime.cn/mt/2015/09/10/094704.35271733_1280X720X2.jpg
         * releaseDate : 9月13日上映
         * rYear : 2015
         * rMonth : 9
         * rDay : 13
         * type : 动画 | 喜剧 | 家庭
         * director : 凯尔·巴尔达
         * actor1 : 桑德拉·布洛克
         * actor2 : 乔·哈姆
         * locationName : 美国
         * isTicket : true
         * wantedCount : 8243
         * videoCount : 3
         * videos : [{"videoId":55397,"url":"http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714_480.mp4","hightUrl":"http://vfx.mtime
         * isVideo : true
         */

        private int id;
        private String title;
        private String image;
        private String releaseDate;
        private int rYear;
        private int rMonth;
        private int rDay;
        private String type;
        private String director;
        private String actor1;
        private String actor2;
        private String locationName;
        private boolean isTicket;
        private int wantedCount;
        private int videoCount;
        private boolean isVideo;
        private List<VideosEntity> videos;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public void setIsTicket(boolean isTicket) {
            this.isTicket = isTicket;
        }

        public void setWantedCount(int wantedCount) {
            this.wantedCount = wantedCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public void setIsVideo(boolean isVideo) {
            this.isVideo = isVideo;
        }

        public void setVideos(List<VideosEntity> videos) {
            this.videos = videos;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int getRYear() {
            return rYear;
        }

        public int getRMonth() {
            return rMonth;
        }

        public int getRDay() {
            return rDay;
        }

        public String getType() {
            return type;
        }

        public String getDirector() {
            return director;
        }

        public String getActor1() {
            return actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public String getLocationName() {
            return locationName;
        }

        public boolean getIsTicket() {
            return isTicket;
        }

        public int getWantedCount() {
            return wantedCount;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public boolean getIsVideo() {
            return isVideo;
        }

        public List<VideosEntity> getVideos() {
            return videos;
        }

        public static class VideosEntity {
            /**
             * videoId : 55397
             * url : http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714_480.mp4
             * hightUrl : http://vfx.mtime.cn/Video/2015/07/24/mp4/150724094629421714.mp4
             * image : http://img31.mtime.cn/mg/2015/07/24/095123.38814335.jpg
             * length : 29
             * title : 小黄人大眼萌 中国版预告片
             */

            private int videoId;
            private String url;
            private String hightUrl;
            private String image;
            private int length;
            private String title;

            public void setVideoId(int videoId) {
                this.videoId = videoId;
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

            public void setLength(int length) {
                this.length = length;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getVideoId() {
                return videoId;
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

            public int getLength() {
                return length;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
