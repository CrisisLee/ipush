package ipush.model;

/**
 * 客户类，是消息发送的目标
 * @author arlabsurface
 *
 */
public class Member {
	
    private Integer id;//id,数据库存储主键

    private Integer createUserId;//创建者id

    private String memberName;//客户名

    private String mobileNum;//电话

    private String email;//邮箱

    private String openId;//微信openid

    private String weiboId;//微博id

    /**
     * channel_prop字段采用Byte形式存储，用于标识用户都有哪些渠道的联系方式。
     * 使用二进制形式构造，对照方式为：5-8位置0，微信为第四位，微博为第三位，短信为
     * 第二位，邮件为第一位，有对应联系方式，对应位为1，否则为0，如：只有短信联系方式
     * ，则存储为0000 0010，对应十进制值为2.
     */
    private Byte channelProp;//根据各个联系方式计算出来的 - 联系属性
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum == null ? null : mobileNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId == null ? null : weiboId.trim();
    }

    public Byte getChannelProp() {
        return channelProp;
    }

    public void setChannelProp(Byte channelProp) {
        this.channelProp = channelProp;
    }


	@Override
	public String toString() {
		return "Member [id=" + id + ", createUserId=" + createUserId + ", memberName=" + memberName + ", mobileNum="
				+ mobileNum + ", email=" + email + ", openId=" + openId + ", weiboId=" + weiboId + ", channelProp="
				+ channelProp + "]";
	}

	public Member(Integer id, Integer createUserId, String memberName, String mobileNum, String email, String openId,
			String weiboId, Byte channelProp) {
		super();
		this.id = id;
		this.createUserId = createUserId;
		this.memberName = memberName;
		this.mobileNum = mobileNum;
		this.email = email;
		this.openId = openId;
		this.weiboId = weiboId;
		this.channelProp = channelProp;
	}
	//兼容数据库，无实际意义
	public Member(Integer id, Integer createUserId, String memberName, String mobileNum, String email, String openId,
			String weiboId, Integer channelProp) {
		super();
		this.id = id;
		this.createUserId = createUserId;
		this.memberName = memberName;
		this.mobileNum = mobileNum;
		this.email = email;
		this.openId = openId;
		this.weiboId = weiboId;
		this.channelProp = Byte.parseByte("" + channelProp);
	}
    
    
    
}