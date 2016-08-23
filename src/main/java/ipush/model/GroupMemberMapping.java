package ipush.model;

/**
 * 组和客户的对照关系，冗余存储该关系，便于查找
 * @author arlabsurface
 *
 */
public class GroupMemberMapping {
    
	private Integer id;//数据库中记录的主键，记录id

    private Integer groupId;//组的id

    private Integer memberId;//客户的id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

	public GroupMemberMapping(Integer groupId, Integer memberId) {
		super();
		this.id = null;
		this.groupId = groupId;
		this.memberId = memberId;
	}
    
    
    
}