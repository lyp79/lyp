package lyp.entity;

import java.util.ArrayList;
import java.util.List;

public class GoodsType {
	private int typeId;
	private String typeName;
	private List<GoodsInfo> list = new ArrayList<GoodsInfo>();
	
	public List<GoodsInfo> getList() {
		return list;
	}
	public void setList(List<GoodsInfo> list) {
		this.list = list;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
