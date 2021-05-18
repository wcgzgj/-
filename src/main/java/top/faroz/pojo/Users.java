package top.faroz.pojo;



public class Users {
    private Integer userId;
    private String loginName;
    private String password;
    private String realName;
    private Integer sex;
    private String email;
    private String address;
    private String phone;
    private String cardId;
    private String desc;
    private Integer roleId;

    //这里，要有一个对象，指向 roleId代表的Role对象
    private Role role;

    public Users(Integer userId, String loginName, String password, String realName, Integer sex, String email, String address, String phone, String cardId, String desc, Integer roleId, Role role) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
        this.realName = realName;
        this.sex = sex;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.cardId = cardId;
        this.desc = desc;
        this.roleId = roleId;
        this.role = role;
    }

    public Users() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cardId='" + cardId + '\'' +
                ", desc='" + desc + '\'' +
                ", roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
