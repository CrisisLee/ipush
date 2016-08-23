package ipush.model;

import java.sql.Timestamp;
import java.util.Date;
/**
 * 客户组类
 * @author arlabsurface
 *
 */
public class Group {
	
	//发送渠道常量
	public static final int CHANNEL_WEIXIN = 8;
	public static final int CHANNEL_WEIBO = 4;
	public static final int CHANNEL_SMS = 2;
	public static final int CHANNEL_EMAIL = 1;
	
	
    private Integer id;//组id

    private String name;//组名

    private Integer userId;//创建组的用户的id

    private Date createTime;//创建时间

    private Integer channel;//发送渠道
    
    private Integer count;//组内人数
    
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

	public Group(Integer id, String name, Integer userId, Date createTime, Integer channel, Integer count) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.createTime = createTime;
		this.channel = channel;
		this.count = count;
	}
	//为了兼容数据库，数据库中读出的时间为timestamp类型
	public Group(Integer id, String name, Integer userId, Timestamp createTime, Integer channel, Integer count) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.createTime = new Date(createTime.getTime());
		this.channel = channel;
		this.count = count;
	}
    
    
}