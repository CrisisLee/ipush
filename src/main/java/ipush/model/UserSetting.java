package ipush.model;

/**
 * 用户配置类
 * 1. 用于记录用户的配置信息
 * @author arlabsurface
 *
 */
public class UserSetting {
    private Integer id;//记录的id，就是用户的id

    private String domain;//邮箱域名（目前来说基本作废，没法自定义域名发送邮件）

    private String sign;//短信签名（同样基本作废，submail不支持自定义短信签名）

    private String weixinAppId;//微信的appid

    private String weixinAppSecret;//微信的appsecret

    private String weiboAppKey;//微博的appkey

    private String weiboAppSecret;//微博的appsecret

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getWeixinAppId() {
        return weixinAppId;
    }

    public void setWeixinAppId(String weixinAppId) {
        this.weixinAppId = weixinAppId == null ? null : weixinAppId.trim();
    }

    public String getWeixinAppSecret() {
        return weixinAppSecret;
    }

    public void setWeixinAppSecret(String weixinAppSecret) {
        this.weixinAppSecret = weixinAppSecret == null ? null : weixinAppSecret.trim();
    }

    public String getWeiboAppKey() {
        return weiboAppKey;
    }

    public void setWeiboAppKey(String weiboAppKey) {
        this.weiboAppKey = weiboAppKey == null ? null : weiboAppKey.trim();
    }

    public String getWeiboAppSecret() {
        return weiboAppSecret;
    }

    public void setWeiboAppSecret(String weiboAppSecret) {
        this.weiboAppSecret = weiboAppSecret == null ? null : weiboAppSecret.trim();
    }

	public UserSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSetting(Integer id, String domain, String sign, String weixinAppId, String weixinAppSecret,
			String weiboAppKey, String weiboAppSecret) {
		super();
		this.id = id;
		this.domain = domain;
		this.sign = sign;
		this.weixinAppId = weixinAppId;
		this.weixinAppSecret = weixinAppSecret;
		this.weiboAppKey = weiboAppKey;
		this.weiboAppSecret = weiboAppSecret;
	}
    
    
}