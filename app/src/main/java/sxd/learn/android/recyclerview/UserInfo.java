package sxd.learn.android.recyclerview;

/**
 * Jackie
 * 2016/8/9
 */
public class UserInfo {
    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 是否选中
     */
    private boolean isCheck;

    public UserInfo() {
    }

    public UserInfo(String name, String sex, boolean isCheck) {
        this.name = name;
        this.sex = sex;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
