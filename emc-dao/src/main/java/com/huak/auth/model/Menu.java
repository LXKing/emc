package com.huak.auth.model;



public class Menu {
    private String id;

    private String menuName;

    private String menuUrl;

    private String pMenuId;

    private String creator;

    private String creatTime;

    private Byte menuType;

    private String pMenuAfId;

    public Menu(String id, String menuName, String menuUrl, String pMenuId, String creator, String creatTime, Byte menuType, String pMenuAfId) {
        this.id = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.pMenuId = pMenuId;
        this.creator = creator;
        this.creatTime = creatTime;
        this.menuType = menuType;
        this.pMenuAfId = pMenuAfId;
    }

    public Menu() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(String pMenuId) {
        this.pMenuId = pMenuId == null ? null : pMenuId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Byte getMenuType() {
        return menuType;
    }

    public void setMenuType(Byte menuType) {
        this.menuType = menuType;
    }

    public String getpMenuAfId() {
        return pMenuAfId;
    }

    public void setpMenuAfId(String pMenuAfId) {
        this.pMenuAfId = pMenuAfId == null ? null : pMenuAfId.trim();
    }
}