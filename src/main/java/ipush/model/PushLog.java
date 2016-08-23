package ipush.model;

import java.util.Date;

/**
 * 推送记录类
 * @author arlabsurface
 *
 */
public class PushLog {
    
	//推送状态常量
	public static final int STATUS_SUCCESS = 0;//推送成功
	public static final int STATUS_FAILED = 1;//推送失败
	
	private Integer id;//记录id

    private Integer messageId;//消息的id

    private Date pushTime;//实际推送的时间

    private Integer createUserId;//创建该消息的用户id

    private Integer toMemberId;//目标客户的id

    private Integer status;//推送的状态：成功/失败

    private Integer pushChannel;//推送的渠道

    private Integer toGroupId;//推送的组的id
	
	
	/**
	 * @param id
	 * @param messageId
	 * @param pushTime
	 * @param createUserId
	 * @param toMemberId
	 * @param status
	 * @param pushChannel
	 * @param toGroupId
	 */
	public PushLog(Integer id, Integer messageId, Date pushTime, Integer createUserId, Integer toMemberId,
			Integer status, Integer pushChannel, Integer toGroupId) {
		super();
		this.id = id;
		this.messageId = messageId;
		this.pushTime = pushTime;
		this.createUserId = createUserId;
		this.toMemberId = toMemberId;
		this.status = status;
		this.pushChannel = pushChannel;
		this.toGroupId = toGroupId;
	}



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(Integer toMemberId) {
        this.toMemberId = toMemberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(Integer pushChannel) {
        this.pushChannel = pushChannel;
    }

    public Integer getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
    }
}