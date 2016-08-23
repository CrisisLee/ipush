package ipush.model;

/**
 * 消息内容类
 * 1. 再群发中，多个消息可能对应着同样的内容，分开定义可以减少冗余
 * @author arlabsurface
 *
 */
public class MessageContent {
    
	private Integer id;//内容的id

	/**
	 * 内容的类型分为6类，
	 * 1为text，纯文本类型的消息，以文本类型存储于content字段；
	 * 2为image，图像类型的信息，存储图片文件对应的url文本类型于content字段；
	 * 3为voice，语音类型的信息，存储语音文件对应的url文本于content字段；
	 * 4为video，视频类型的信息，存储视频文件对应的url文本于content字段；
	 * 5为html，富文本类型的信息，包括文字、图片、语音、视频等综合信息，以html格式解析内容，以文本形式存储于content字段；
	 * 6为t_msg，微信模版消息，需要以id和对应json字符串的形式存储，格式为文本，存储于tmsg_content字段；
	 * 7为news，多图文消息，只需提供多图文对应id，以整型的news_id存储。
	 */
    private Integer type;//内容的类型

    private Integer newsId;//图文消息的id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

	/**
	 * @param id
	 * @param type
	 * @param newsId
	 */
	public MessageContent(Integer id, Integer type, Integer newsId) {
		super();
		this.id = id;
		this.type = type;
		this.newsId = newsId;
	}
    
    
}