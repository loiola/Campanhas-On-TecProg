package control.servlet.listpage;

import javax.servlet.http.HttpServletRequest;

import log.general.ControlLogger;

public class ListPaginationLogic {

	/*
	 * This Class have all the methods and attributes logics to receive and
	 * respond to the user interaction with lists paginations
	 */

	// Constants
	public static final String STANDARD_EXPECTED_PAGINATION_NAME = "pagination";
	private static final String FIRST_PAGE_OF_THE_LIST_NAME = "__first_page";
	private static final String QUANTITY_OF_TERMS_PER_PAGE_NAME = "__qtd_of_terms";
	private static final String SEE_ALL_TRIGGER_NAME = "__see_all";
	private static final String CENTER_OF_PAGES_LISTED_NAME = "__center_page";

	/**
	 * This method is responsible for update list of a pagination
	 * @param  requestReference
	 * 			 values received from the View
	 * @param listSize
	 *			 size of the quantity of terms per page
	 * @param paginationExpectedName
	 * 			  name of the page expected  
	 * @return a request
	 */
	public static HttpServletRequest updatePaginationList(
			HttpServletRequest requestReference, Integer listSize,
			String paginationExpectedName) {
		HttpServletRequest request = requestReference;
		Pagination pagination = generatePaginationValues(request, listSize,
				paginationExpectedName);
		request.setAttribute(paginationExpectedName, pagination);

		return request;
	}

	/**
	 * This method is responsible for generate the values of pagination
	 * @param request
	 *            values received from the View
	 * @param listSize 
	 * 			  size of the quantity of terms per page 
	 * @param paginationExpectedName
	 * 			  name of the page expected 			  
	 * @return a pagination
	 */
	private static Pagination generatePaginationValues(
			HttpServletRequest request, Integer listSize,
			String paginationExpectedName) {
		Pagination pagination = new Pagination();

		Integer firstPageOfTheList = Integer.parseInt(request
				.getParameter(paginationExpectedName
						+ FIRST_PAGE_OF_THE_LIST_NAME));
		pagination.setFirstPageOfTheList(firstPageOfTheList);

		Boolean seeAllTermsOfTheList = Boolean.parseBoolean(request
				.getParameter(paginationExpectedName + SEE_ALL_TRIGGER_NAME));
		pagination.setSeeAllTermsOfTheList(seeAllTermsOfTheList);

		Integer quantityOfTermsPerPage = 0;
		if (pagination.isSeeAllTermsOfTheList() == true) {
			quantityOfTermsPerPage = listSize;
		} else {
			quantityOfTermsPerPage = Integer.parseInt(request
					.getParameter(paginationExpectedName
							+ QUANTITY_OF_TERMS_PER_PAGE_NAME));
		}
		pagination.setQuantityOfTermsPerPage(quantityOfTermsPerPage);

		Integer centerOfPagesListed = Integer.parseInt(request
				.getParameter(paginationExpectedName
						+ CENTER_OF_PAGES_LISTED_NAME));
		pagination.setCenterOfPagesListed(centerOfPagesListed);

		pagination.setIndexOfPages(generateListIndex(listSize,
				pagination.getQuantityOfTermsPerPage()));

		pagination
				.setQuantityOfPagesListedInThePage(generateQuantityOfPagesListedInThePage(listSize));

		pagination = generateQuantityOfPagesListedRadius(pagination);

		return pagination;
	}

	/**
	 * This method is responsible for generate list index
	 * @param listSize 
	 * 			  size of the quantity of terms per page
	 * @param divider
	 * 			 number responsible for division
	 * @return integer quantity of list index 
	 */
	private static Integer generateListIndex(Integer listSize, Integer divider) {
		Integer listIndex = 0;
		if (divider != 0) {
			listIndex = (int) Math.ceil((double) listSize / (double) divider);
		} else {
			listIndex = 1;
		}
		return listIndex;
	}

	/**
	 * This method is responsible for generate quantity of pages listed in the page
	 * @param listSize 
	 * 			  size of the quantity of terms per page
	 * @return integer quantity of index per page
	 */
	private static Integer generateQuantityOfPagesListedInThePage(
			Integer listSize) {
		Integer dividerReferenceForPageIndex = 25;
		Integer pageIndex = (int) Math.floor((double) listSize
				/ (double) dividerReferenceForPageIndex);

		pageIndex = checkAndLimitPageIndex(pageIndex);

		return pageIndex;
	}

	/**
	 * This method is responsible for delimit the number of index per page
	 * @param pageIndexReference
	 * 				 page of reference
	 * @return integer quantity of index per page
	 */
	private static Integer checkAndLimitPageIndex(Integer pageIndexReference) {
		Integer pageIndex = pageIndexReference;
		if (pageIndex < 0) {
			/*
			 * If this condition trigger, is suspicious! Probably there's an
			 * error in the View because the pageIndex should not be negative
			 */
			pageIndex = 0;
			ControlLogger.warn(ControlLogger.SERVLET_LOG_STRING,
					ControlLogger.CONDITION_IF_HANDLED);
		} else if (pageIndex >= 4 && pageIndex < 10) {
			pageIndex = 4;
		} else if (pageIndex >= 10 && pageIndex < 20) {
			pageIndex = 5;
		} else if (pageIndex >= 20 && pageIndex < 40) {
			pageIndex = 6;
		} else if (pageIndex >= 40 && pageIndex < 80) {
			pageIndex = 7;
		} else if (pageIndex >= 80) {
			pageIndex = 8;
		} else {
			/*
			 * Do Nothing, if the index of Pages is below to 4 and above 0,
			 * there's no need to limit
			 */

		}
		return pageIndex;
	}

	/**
	 * This method is responsible for generate the quantity of pages listed radius
	 * @param  pagination
	 * 				 page for analyzes 
	 * @return a pagination
	 */
	private static Pagination generateQuantityOfPagesListedRadius(
			Pagination pagination) {
		Integer counter = 0;
		if (pagination.getIndexOfPages() > 9) {
			counter = 9;
		} else {
			counter = pagination.getIndexOfPages() - 1;
		}

		Integer centerOfPagesListedToMinimum = pagination
				.getCenterOfPagesListed();
		Integer centerOfPagesListedToMaximum = pagination
				.getCenterOfPagesListed();
		Integer minimumRadius = 0;
		Integer maximumRadius = 0;

		while (counter != 0) {
			if (centerOfPagesListedToMinimum == 1)
				maximumRadius++;
			else if (minimumRadius < 5) {
				minimumRadius++;
				centerOfPagesListedToMinimum--;
			} else if (maximumRadius == pagination.getIndexOfPages()) {
				minimumRadius++;
			} else {
				maximumRadius++;
				centerOfPagesListedToMaximum++;
			}
			counter--;
		}
		maximumRadius += pagination.getCenterOfPagesListed();
		minimumRadius = pagination.getCenterOfPagesListed() - minimumRadius;

		pagination.setMinimumRadiusOfPagesListed(minimumRadius);
		pagination.setMaximumRadiusOfPagesListed(maximumRadius);

		return pagination;
	}

}
