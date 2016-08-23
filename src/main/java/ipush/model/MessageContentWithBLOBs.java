package ipush.model;

/**
 * 带有text文本的消息内容类
 * 1. 继承了消息内容类
 * @author arlabsurface
 *
 */
public class MessageContentWithBLOBs extends MessageContent {
    
	private String content;//消息体，具体的内容

    private String tmsgContent;//如果类型为为新模板消息，则为模板消息的内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTmsgContent() {
        return tmsgContent;
    }

    public void setTmsgContent(String tmsgContent) {
        this.tmsgContent = tmsgContent == null ? null : tmsgContent.trim();
    }

	/**
	 * @param id
	 * @param type
	 * @param newsId
	 * @param content
	 * @param tmsgContent
	 */
	public MessageContentWithBLOBs(Integer id, Integer type, Integer newsId, String content, String tmsgContent) {
		super(id, type, newsId);
		this.content = content;
		this.tmsgContent = tmsgContent;
	}
    
    
    
}