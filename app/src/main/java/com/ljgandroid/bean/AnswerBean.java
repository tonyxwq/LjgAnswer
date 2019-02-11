package com.ljgandroid.bean;

/**
 * Author:XWQ
 * Time   2019/1/17
 * Descrition: this is AnswerBean
 */

public class AnswerBean
{

    /**
     * success : true
     * message : ok
     * code : 0
     * value : null
     */

    private boolean success;
    private String message;
    private int code;
    private Object value;

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
}
