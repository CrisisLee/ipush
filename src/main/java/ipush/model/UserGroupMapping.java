package ipush.model;

/**
 * 用户于组对照表
 * 1. 用于冗余存储用户和用户创建的组的对照关系，方便查找
 * @author arlabsurface
 *
 */
public class UserGroupMapping {
    private Integer id;//记录的id，就是组的id，因为每个组只能属于一个用户，所以组的id可以作为主键

    private Integer userId;//用户id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public UserGroupMapping(Integer id, Integer userId) {
		super();
		this.id = id;
		this.userId = userId;
	}
    
    
}