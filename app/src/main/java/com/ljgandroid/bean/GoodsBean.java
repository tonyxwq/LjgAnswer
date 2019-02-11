package com.ljgandroid.bean;

/**
 * Author:XWQ
 * Time   2019/1/29
 * Descrition: this is GoodsBean
 */

public class GoodsBean
{
    public Up up;
    public Down down;

    public Up getUp()
    {
        return up;
    }

    public void setUp(Up up)
    {
        this.up = up;
    }

    public Down getDown()
    {
        return down;
    }

    public void setDown(Down down)
    {
        this.down = down;
    }

    public class Up
    {
        private int url;
        private String name;

        public int getUrl()
        {
            return url;
        }

        public void setUrl(int url)
        {
            this.url = url;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }

    public class Down
    {
        private int url;
        private String name;

        public int getUrl()
        {
            return url;
        }

        public void setUrl(int url)
        {
            this.url = url;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }
}
