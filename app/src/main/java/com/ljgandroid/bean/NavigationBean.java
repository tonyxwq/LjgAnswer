package com.ljgandroid.bean;

/**
 * Author:XWQ
 * Time   2019/1/10
 * Descrition: this is NavigationBean
 */

public class NavigationBean
{
    public boolean isVisibile()
    {
        return visibile;
    }

    public void setVisibile(boolean visibile)
    {
        this.visibile = visibile;
    }

    private boolean visibile;
    private int start;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    private int end;
    private String msg;

    public int getCell()
    {
        return cell;
    }

    public void setCell(int cell)
    {
        this.cell = cell;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    private int index;
    private int cell;
    private int width;

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getEnd()
    {
        return end;
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
}
