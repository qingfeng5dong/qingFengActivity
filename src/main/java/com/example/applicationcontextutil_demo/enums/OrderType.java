package com.example.applicationcontextutil_demo.enums;


public enum OrderType {

    takeaway("外卖", 1), advanceOrder("预订单", 2), shoppingMall("商城", 3), pickUp("自提", 4), dineIn("堂吃", 5);

    private String name;
    private int index;

    private OrderType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }



    public static String getClassName(int index)  {
        switch (index) {
            case 1:
                return "activityOrderImpl";
            case 2:
                return "preOrderImpl";
            case 3:
                return "bankOrderServiceImpl";
            case 4:
                return "orderServiceImpl";

            default:
                return "无效参数";
        }
    }
}
