package com.ljgandroid.bean;

import java.util.List;

/**
 * Author:XWQ
 * Time   2019/1/4
 * Descrition: this is JavaBean
 */

public class JavaBean extends BasicResponse
{

    /**
     * code : 1
     * message : success
     * notice : {"newsCount":23}
     * result : {"items":[{"detail":"","href":"https://www.oschina.net/question/2720166_2302201","id":2302201,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_987cdd9e-21d4-41f0-987c-483bd03f49aa.jpg","name":" 探究 RocketMQ 的架构设计与实现原理","pubDate":"2019-01-02 09:16:24","type":2,"weight":5},{"detail":"","href":"https://www.oschina.net/news/103267/postgresql-is-the-dbms-of-the-year-2018","id":103267,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_f3816802-c9c2-4a32-8d0e-969ff3663a98.jpg","name":"PostgreSQL 蝉联\u201c年度数据库\u201d称号","pubDate":"2019-01-03 11:12:53","type":6,"weight":4},{"detail":"","href":"https://www.oschina.net/translate/50-data-structure-and-algorithms-interview-questions","id":4469,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_8594973e-eb4a-4713-8dc6-f7b4a1ff28d8.jpg","name":"为工程师准备的 50 道数据结构和算法面试题 ","pubDate":"2019-01-03 14:43:19","type":4,"weight":3},{"detail":"","href":"https://www.oschina.net/news/103266/using-python-on-windows","id":103266,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_0b2c9cd6-ec02-4aa9-ae72-6703015dc954.jpg","name":"Python 3.7 上架微软商店，尚处于评估阶段","pubDate":"2019-01-03 14:45:12","type":6,"weight":2},{"detail":"","href":"https://www.oschina.net/p/photon-aria2","id":48089,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_487204a0-c462-49f3-817c-4a32b231df3f.jpg","name":" Photon aria2\u2014\u2014基于 aria2 的多线程下载软件 ","pubDate":"2019-01-02 09:51:29","type":1,"weight":1}],"nextPageToken":"61AF0C190D6BD629","prevPageToken":"3EA621243546C8A5","requestCount":5,"responseCount":5,"totalResults":5}
     * time : 2019-01-04 10:35:34
     */

    private int code;
    private String message;
    private NoticeBean notice;
    private ResultBean result;
    private String time;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public NoticeBean getNotice()
    {
        return notice;
    }

    public void setNotice(NoticeBean notice)
    {
        this.notice = notice;
    }

    public ResultBean getResult()
    {
        return result;
    }

    public void setResult(ResultBean result)
    {
        this.result = result;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public static class NoticeBean
    {
        /**
         * newsCount : 23
         */

        private int newsCount;

        public int getNewsCount()
        {
            return newsCount;
        }

        public void setNewsCount(int newsCount)
        {
            this.newsCount = newsCount;
        }
    }

    public static class ResultBean
    {
        @Override
        public String toString()
        {
            return "ResultBean{" +
                    "nextPageToken='" + nextPageToken + '\'' +
                    ", prevPageToken='" + prevPageToken + '\'' +
                    ", requestCount=" + requestCount +
                    ", responseCount=" + responseCount +
                    ", totalResults=" + totalResults +
                    ", items=" + items +
                    '}';
        }

        /**
         * items : [{"detail":"","href":"https://www.oschina.net/question/2720166_2302201","id":2302201,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_987cdd9e-21d4-41f0-987c-483bd03f49aa.jpg","name":" 探究 RocketMQ 的架构设计与实现原理","pubDate":"2019-01-02 09:16:24","type":2,"weight":5},{"detail":"","href":"https://www.oschina.net/news/103267/postgresql-is-the-dbms-of-the-year-2018","id":103267,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_f3816802-c9c2-4a32-8d0e-969ff3663a98.jpg","name":"PostgreSQL 蝉联\u201c年度数据库\u201d称号","pubDate":"2019-01-03 11:12:53","type":6,"weight":4},{"detail":"","href":"https://www.oschina.net/translate/50-data-structure-and-algorithms-interview-questions","id":4469,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_8594973e-eb4a-4713-8dc6-f7b4a1ff28d8.jpg","name":"为工程师准备的 50 道数据结构和算法面试题 ","pubDate":"2019-01-03 14:43:19","type":4,"weight":3},{"detail":"","href":"https://www.oschina.net/news/103266/using-python-on-windows","id":103266,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_0b2c9cd6-ec02-4aa9-ae72-6703015dc954.jpg","name":"Python 3.7 上架微软商店，尚处于评估阶段","pubDate":"2019-01-03 14:45:12","type":6,"weight":2},{"detail":"","href":"https://www.oschina.net/p/photon-aria2","id":48089,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_487204a0-c462-49f3-817c-4a32b231df3f.jpg","name":" Photon aria2\u2014\u2014基于 aria2 的多线程下载软件 ","pubDate":"2019-01-02 09:51:29","type":1,"weight":1}]
         * nextPageToken : 61AF0C190D6BD629
         * prevPageToken : 3EA621243546C8A5
         * requestCount : 5
         * responseCount : 5
         * totalResults : 5
         */



        private String nextPageToken;
        private String prevPageToken;
        private int requestCount;
        private int responseCount;
        private int totalResults;
        private List<ItemsBean> items;

        public String getNextPageToken()
        {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken)
        {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken()
        {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken)
        {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount()
        {
            return requestCount;
        }

        public void setRequestCount(int requestCount)
        {
            this.requestCount = requestCount;
        }

        public int getResponseCount()
        {
            return responseCount;
        }

        public void setResponseCount(int responseCount)
        {
            this.responseCount = responseCount;
        }

        public int getTotalResults()
        {
            return totalResults;
        }

        public void setTotalResults(int totalResults)
        {
            this.totalResults = totalResults;
        }

        public List<ItemsBean> getItems()
        {
            return items;
        }

        public void setItems(List<ItemsBean> items)
        {
            this.items = items;
        }

        public static class ItemsBean
        {
            @Override
            public String toString()
            {
                return "ItemsBean{" +
                        "detail='" + detail + '\'' +
                        ", href='" + href + '\'' +
                        ", id=" + id +
                        ", img='" + img + '\'' +
                        ", name='" + name + '\'' +
                        ", pubDate='" + pubDate + '\'' +
                        ", type=" + type +
                        ", weight=" + weight +
                        '}';
            }

            /**
             * detail :
             * href : https://www.oschina.net/question/2720166_2302201
             * id : 2302201
             * img : https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_987cdd9e-21d4-41f0-987c-483bd03f49aa.jpg
             * name :  探究 RocketMQ 的架构设计与实现原理
             * pubDate : 2019-01-02 09:16:24
             * type : 2
             * weight : 5
             */




            private String detail;
            private String href;
            private int id;
            private String img;
            private String name;
            private String pubDate;
            private int type;
            private int weight;

            public String getDetail()
            {
                return detail;
            }

            public void setDetail(String detail)
            {
                this.detail = detail;
            }

            public String getHref()
            {
                return href;
            }

            public void setHref(String href)
            {
                this.href = href;
            }

            public int getId()
            {
                return id;
            }

            public void setId(int id)
            {
                this.id = id;
            }

            public String getImg()
            {
                return img;
            }

            public void setImg(String img)
            {
                this.img = img;
            }

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }

            public String getPubDate()
            {
                return pubDate;
            }

            public void setPubDate(String pubDate)
            {
                this.pubDate = pubDate;
            }

            public int getType()
            {
                return type;
            }

            public void setType(int type)
            {
                this.type = type;
            }

            public int getWeight()
            {
                return weight;
            }

            public void setWeight(int weight)
            {
                this.weight = weight;
            }
        }
    }
}
