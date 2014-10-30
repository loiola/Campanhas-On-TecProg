package control.servlet.listpage;

public class Pagination {
	
	private final Integer STANDARD_FIRST_PAGE_OF_THE_LIST_VALUE = 0;
	private final Integer STANDARD_QUANTITY_OF_TERMS_PER_PAGE = 10;
	private final Boolean STANDARD_SEE_ALL_CONDITION = false;
	private final Integer STANDARD_CENTER_OF_PAGES_LISTED = 1;
	private final Integer STANDARD_VALUE_FOR_ALGORITHM_ATTRIBUTES = 0;

	private Integer firstPageOfTheList;
	private Integer quantityOfTermsPerPage;
	
	private Boolean seeAllTermsOfTheList;
	
	private Integer centerOfPagesListed;
	
	private Integer indexOfPages;
	private Integer quantityOfPagesListedInThePage;
	
	private Integer minimumRadiusOfPagesListed;
	private Integer maximumRadiusOfPagesListed;
	
	protected Pagination() {
		this.firstPageOfTheList = STANDARD_FIRST_PAGE_OF_THE_LIST_VALUE;
		this.quantityOfTermsPerPage = STANDARD_QUANTITY_OF_TERMS_PER_PAGE;
		this.seeAllTermsOfTheList = STANDARD_SEE_ALL_CONDITION;
		this.centerOfPagesListed = STANDARD_CENTER_OF_PAGES_LISTED;
		
		this.indexOfPages = STANDARD_VALUE_FOR_ALGORITHM_ATTRIBUTES;
		this.quantityOfPagesListedInThePage = STANDARD_VALUE_FOR_ALGORITHM_ATTRIBUTES;
		
		this.minimumRadiusOfPagesListed = STANDARD_VALUE_FOR_ALGORITHM_ATTRIBUTES;
		this.maximumRadiusOfPagesListed = STANDARD_VALUE_FOR_ALGORITHM_ATTRIBUTES;
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
