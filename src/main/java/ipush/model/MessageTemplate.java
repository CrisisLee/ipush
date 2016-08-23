package ipush.model;

/**
 * 消息模板类
 * 1. 该类还没有引入到系统中
 * @author arlabsurface
 *
 */
public class MessageTemplate {
    
	private Integer id;//模板的id

    private String name;//模板名称

    private Integer level;//模板的级别（对应于用户会员级别）

    private String title;//模板标题

    private Integer contentId;////内容

    private byte[] view;//模板的缩略图

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public byte[] getView() {
        return view;
    }

    public void setView(byte[] view) {
        this.view = view;
    }
}