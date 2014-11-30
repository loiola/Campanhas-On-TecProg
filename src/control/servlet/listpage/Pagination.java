package control.servlet.listpage;

public class Pagination {
	
	/*
	 * This class contain the logic pagination of application tables
	 */
	
	// Constants
	private final Integer STANDARD_FIRST_PAGE_OF_THE_LIST_VALUE = 0;
	private final Integer STANDARD_QUANTITY_OF_TERMS_PER_PAGE = 10;
	private final Boolean STANDARD_SEE_ALL_CONDITION = false;
	private final Integer STANDARD_CENTER_OF_PAGES_LISTED = 1;
	private final Integer STANDARD_VALUE_FOR_ALGORITHM_ATTRIBUTES = 0;

	//  Attributes
	
	// Variable that characterizes the first page of the list that show in application
	private Integer firstPageOfTheList;
	
	//Variable that characterizes quantity of terms that shows per page
	private Integer quantityOfTermsPerPage;
	
	// Variable that characterizes all terms of the list that shows per page
	private Boolean seeAllTermsOfTheList;
	
	// Variable that characterizes median of pages listed number
	private Integer centerOfPagesListed;
	
	// Variable that characterizes the page index 
	private Integer indexOfPages;
	
	// Variable that characterizes the number of pages listed in the page
	private Integer quantityOfPagesListedInThePage;
	
	// Variable that characterizes the number minimum of pages displayed in page
	private Integer minimumRadiusOfPagesListed;
	
	// Variable that characterizes the number maximum of pages displayed in page
	private Integer maximumRadiusOfPagesListed;
	
	// Constructors
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

	// Getters and Setters
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