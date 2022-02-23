package com.bjpowernode.pojo.vo;

public class ProdictinfoVo {
    private String pname;
    private Integer typeid;
    private Integer Iprice;
    private Integer hprice;

    public ProdictinfoVo(String pname, Integer typeid, Integer iprice, Integer hprice) {
        this.pname = pname;
        this.typeid = typeid;
        Iprice = iprice;
        this.hprice = hprice;
    }

    public ProdictinfoVo() {
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getIprice() {
        return Iprice;
    }

    public void setIprice(Integer iprice) {
        Iprice = iprice;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    @Override
    public String toString() {
        return "ProdictinfoVo{" +
                "pname='" + pname + '\'' +
                ", typeid=" + typeid +
                ", Iprice=" + Iprice +
                ", hprice=" + hprice +
                '}';
    }
}
