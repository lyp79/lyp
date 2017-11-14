package lyp.entity;

import java.util.List;

public class PageModel<T> {
	private int totalRecord;// �ܼ�¼����
	private int pageSize = 10;// ÿҳ��ʾ������
	private int totalPage;// ��ҳ��
	private int pageNo = 1;// ��ǰҳ��
	private List<T> data;
	private int prevPageNo;
	private int nextPageNo;
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (this.totalRecord-1)/this.pageSize+1;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	// ��һҳ
	public int getPrevPageNo() {
		if (pageNo == 1) {
			prevPageNo = pageNo;
			return prevPageNo;
		} else {
			prevPageNo = pageNo - 1;
			return prevPageNo;
		}
	}

	// ��һҳ
	public int getNextPageNo() {
		if (pageNo == totalPage) {
			nextPageNo = pageNo;
			return nextPageNo;
		} else {
			nextPageNo = pageNo + 1;
			return nextPageNo;
		}

	}
}
