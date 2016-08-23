package ipush.model;

import java.util.Date;

/**
 * 用户类，是系统的使用者，消息的发起者
 * @author arlabsurface
 *
 */
public class User {
    private Integer id;//用户id

    /**
     * 主要为了方便系统之后扩展
     * 管理员具有系统中其他用户的管理权限、模板的创建、编辑权限等
     */
    private Boolean isAdmin;//是否是管理员

    private String userName;//用户名，默认为邮箱

    private String password;//密码

    private Integer level;//会员等级

    private String email;//邮箱

    private Date registerTime;//注册时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password) {
		super();
		this.password = password;
		this.email = email;
	}

	public User(Integer id, Boolean isAdmin, String userName, String password, Integer level, String email,
			Date registerTime) {
		super();
		this.id = id;
		this.isAdmin = isAdmin;
		this.userName = userName;
		this.password = password;
		this.level = level;
		this.email = email;
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", isAdmin=" + isAdmin + ", userName=" + userName + ", password=" + password
				+ ", level=" + level + ", email=" + email + ", registerTime=" + registerTime + "]";
	}
    
	
    
    
}