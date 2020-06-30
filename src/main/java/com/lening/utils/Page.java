package com.lening.utils;

import java.util.List;

public final class Page<T> {

	/**
	 * 因为我们重写了有参构造器，系统默认的无参构造器就没有了，
	 * 但是有些事一些第三方插件或者框架底层解析需要无参构造去
	 */

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 我们装数据
	 */
	private List<T> list;

	public static final int DEFAULT_PAGE_SIZE = 5;

	//总记录数
	private int count;
	//每页多少记录
	private int pageSize;
	//总页数
	private int pageCount;
	//当前页码
	private int currentPage;
	//上一页
	private int previousPage;
	//下一页
	private int nextPage;
	//开始记录
	private int startRecord;
	//结束记录
	private int endRecord;


	public Page(String current, int count, String pageSize) {
		this.count = count;
		init(current, pageSize);
	}

	private void init(String current, String size) {
		initPageSize(size);
		initPageCount();
		initCurrentPage(current);
		initPreviousPage();
		initNextPage();
		initStartRecord();
		initEndRecord();
	}

	/**
	 * 我们在servlet中，new page类，然后相当于调了page构造函数，在构造函数中，先初始化了count
	 * 然后又调用了init方法
	 * init中有调用了7个方法，把相关属性全部初始化了
	 * 这个是初始化，每页显示多少记录的，当用户输入为空，或者paseint报异常的，
	 * 或者用户输入小于1的数值时候，都为默认值
	 */

	private void initPageSize(String size) {
		if (size == null || size.trim().isEmpty()) {
			pageSize = DEFAULT_PAGE_SIZE;
			return;
		}
		try {
			pageSize = Integer.parseInt(size);
			if(pageSize<1){
				pageSize = DEFAULT_PAGE_SIZE;
			}
		} catch (Exception e) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
	}

	/**
	 初始化一共分多少页，不能整除，就得向上取整
	 */
	private void initPageCount() {
		pageCount = count / pageSize;
		if (count % pageSize != 0) {
			pageCount++;
		}
	}

	/**
	 * 初始化当前页，也就是从前台页面传递过来的，这个用户可以手动输入，必须严格严格
	 * @param current
	 */
	private void initCurrentPage(String current) {

		if (current == null || current.trim().isEmpty()) {
			currentPage = 1;
			return;
		}

		try {
			currentPage = Integer.parseInt(current);
		} catch (Exception e) {
			currentPage = 1;
		}

		if (currentPage < 1) {
			currentPage = 1;
			return;
		}
		if (currentPage > pageCount) {
			currentPage = pageCount;
		}
	}


	private void initPreviousPage() {
		previousPage = currentPage - 1;
		if (previousPage < 1) {
			previousPage = 1;
		}
	}

	private void initNextPage() {
		nextPage = currentPage + 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
	}




	private void initStartRecord() {
		startRecord = (currentPage - 1) * pageSize;
		if (startRecord < 0) {
			startRecord = 0;
		}
		if (startRecord > count) {
			startRecord = count;
		}
	}

	private void initEndRecord() {
		endRecord = startRecord + pageSize - 1;
		if (endRecord > count) {
			endRecord = count;
		}
	}



	public int getCount() {
		return count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}



	public int getPreviousPage() {
		return previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
