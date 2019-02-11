package com.ljgandroid.bean;

/**
 * Author:XWQ
 * Time   2019/1/30
 * Descrition: this is User
 */

public class User
{
    private String name;
    private int age;

    private BankInfo bankInfo;

    private Org org;

    public class BankInfo
    {
        private String bankNo;
        private String bankName;

        public String getBankNo()
        {
            return bankNo;
        }

        public void setBankNo(String bankNo)
        {
            this.bankNo = bankNo;
        }

        public String getBankName()
        {
            return bankName;
        }

        public void setBankName(String bankName)
        {
            this.bankName = bankName;
        }
    }

    public static class Org
    {
        private String orgCode = "";
        private String orgName = "";

        public void print()
        {
            System.out.println("机构编码：" + orgCode + ",机构名称:" + orgName);
        }

        public String getOrgCode()
        {
            return orgCode;
        }

        public void setOrgCode(String orgCode)
        {
            this.orgCode = orgCode;
        }

        public String getOrgName()
        {
            return orgName;
        }

        public void setOrgName(String orgName)
        {
            this.orgName = orgName;
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public BankInfo getBankInfo()
    {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo)
    {
        this.bankInfo = bankInfo;
    }

    public Org getOrg()
    {
        return org;
    }

    public void setOrg(Org org)
    {
        this.org = org;
    }
}
