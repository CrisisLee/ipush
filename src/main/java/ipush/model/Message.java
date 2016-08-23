package ipush.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 消息类
 * 1. 实现了Delayed接口，可以存储于DelayQueue中，既“只有到了推送时间的消息才能从队列中取出”
 * @author arlabsurface
 *
 */
public class Message implements Delayed {
	
	//消息推送类型常量
	public static final int ORDINARY = 0;//普通消息
	public static final int ADVANCED = 1;//高级消息，周期性推送
	
	//消息的状态常量
	public static final int STATUS_EDITABLE = 0;//可编辑的
	public static final int STATUS_READYTOPUSH = 1;//不可编辑，可以推送
	public static final int STATUS_GOINGTOPUSH = 2;//加入到推送列表中
	public static final int STATUS_PUSHED = 3;//成功推送
	public static final int STATUS_FAILTOPUSH = 4;//推送失败

	//消息发送渠道常量
	public static final int CHANNEL_WEIXIN = 8;//微信
	public static final int CHANNEL_WEIBO = 4;//微博
	public static final int CHANNEL_SMS = 2;//短信
	public static final int CHANNEL_EMAIL = 1;//邮件

	private Integer id;//消息id

	private String title;//消息标题，只有邮件的有意义

	private Integer contentId;//对应的内容的id

	private Integer toGroupId;//目标组的id

	private Integer channel;//发送渠道

	private Date pushTime;//推送时间

	private Integer status;//推送的状态

	private Date createTime;//创建时间

	private Integer createUserId;//创建用户的id

	private Date updateTime;//更新时间

	private String cronExpression;//cron表达式

	private Integer pushType;//推送类型，单次or周期性

	/**
	 * @param id
	 * @param title
	 * @param contentId
	 * @param toGroupId
	 * @param channel
	 * @param pushTime
	 * @param status
	 * @param createTime
	 * @param createUserId
	 * @param updateTime
	 * @param cronExpression
	 * @param pushType
	 */
	public Message(Integer id, String title, Integer contentId, Integer toGroupId, Integer channel, Date pushTime,
			Integer status, Date createTime, Integer createUserId, Date updateTime, String cronExpression,
			Integer pushType) {
		super();
		this.id = id;
		this.title = title;
		this.contentId = contentId;
		this.toGroupId = toGroupId;
		this.channel = channel;
		this.pushTime = pushTime;
		this.status = status;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.updateTime = updateTime;
		this.cronExpression = cronExpression;
		this.pushType = pushType;
	}
	
	//为了兼容数据库 ，无实际意义
	public Message(Integer id, String title, Integer contentId, Integer toGroupId, Integer channel, Timestamp pushTime,
			Integer status, Timestamp createTime, Integer createUserId, Timestamp updateTime, String cronExpression,
			Integer pushType) {
		super();
		this.id = id;
		this.title = title;
		this.contentId = contentId;
		this.toGroupId = toGroupId;
		this.channel = channel;
		this.pushTime = new Date(pushTime.getTime());
		this.status = status;
		this.createTime = new Date(createTime.getTime());
		this.createUserId = createUserId;
		this.updateTime = new Date(updateTime.getTime());
		this.cronExpression = cronExpression;
		this.pushType = pushType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getToGroupId() {
		return toGroupId;
	}

	public void setToGroupId(Integer toGroupId) {
		this.toGroupId = toGroupId;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression == null ? null : cronExpression.trim();
	}

	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long timeLeft = pushTime.getTime() - System.currentTimeMillis();
		if (timeLeft <= 0) {
			return 0;
		}
		long result = unit.convert(timeLeft, TimeUnit.MILLISECONDS);
		return result;
	}
}