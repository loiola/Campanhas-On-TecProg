package control.servlet.listpage;

public class Pagination {

	private Boolean seeAllTermsOfTheList;
	private Integer firstPageOfTheList;
	private Integer quantityOfTermsPerPage;

	private Integer currentPageOfTheList;

	private Integer indexOfPages;
	private Integer quantityOfPagesListedInThePage;
	
	private Integer minimumRadiusOfPagesListed;
	private Integer maximumRadiusOfPagesListed;
	
	private Integer centerOfPagesListed;

	protected Pagination() {
		this.seeAllTermsOfTheList = false;
		this.firstPageOfTheList = 1;
		this.quantityOfTermsPerPage = 10;
		this.currentPageOfTheList = 1;
		this.indexOfPages = 0;
		this.quantityOfPagesListedInThePage = 10;
	}

	public Boolean isSeeAllTermsOfTheList() {
		return seeAllTermsOfTheList;
	}

	public void setSeeAllTermsOfTheList(Boolean seeAllTermsOfTheList) {
		this.seeAllTermsOfTheList = seeAllTermsOfTheList;
	}

	public Integer getFirstPageOfTheList() {
		return firstPageOfTheList;
	}

	public void setFirstPageOfTheList(Integer firstPageOfTheList) {
		this.firstPageOfTheList = firstPageOfTheList;
	}

	public Integer getQuantityOfTermsPerPage() {
		return quantityOfTermsPerPage;
	}

	public void setQuantityOfTermsPerPage(Integer quantityOfTermsPerPage) {
		this.quantityOfTermsPerPage = quantityOfTermsPerPage;
	}

	public Integer getCurrentPageOfTheList() {
		return currentPageOfTheList;
	}

	public void setCurrentPageOfTheList(Integer currentPageOfTheList) {
		this.currentPageOfTheList = currentPageOfTheList;
	}

	public Integer getIndexOfPages() {
		return indexOfPages;
	}

	public void setIndexOfPages(Integer indexOfPages) {
		this.indexOfPages = indexOfPages;
	}

	public Integer getQuantityOfPagesListedInThePage() {
		return quantityOfPagesListedInThePage;
	}

	public void setQuantityOfPagesListedInThePage(
			Integer quantityOfPagesListedInThePage) {
		this.quantityOfPagesListedInThePage = quantityOfPagesListedInThePage;
	}

	public Integer getMinimumRadiusOfPagesListed() {
		return minimumRadiusOfPagesListed;
	}

	public void setMinimumRadiusOfPagesListed(Integer minimumRadiusOfPagesListed) {
		this.minimumRadiusOfPagesListed = minimumRadiusOfPagesListed;
	}

	public Integer getMaximumRadiusOfPagesListed() {
		return maximumRadiusOfPagesListed;
	}

	public void setMaximumRadiusOfPagesListed(Integer maximumRadiusOfPagesListed) {
		this.maximumRadiusOfPagesListed = maximumRadiusOfPagesListed;
	}

	public Integer getCenterOfPagesListed() {
		return centerOfPagesListed;
	}

	public void setCenterOfPagesListed(Integer centerOfPagesListed) {
		this.centerOfPagesListed = centerOfPagesListed;
	}

}
