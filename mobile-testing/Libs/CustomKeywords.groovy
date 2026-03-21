
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String



def static "auth.LoginKeyword.login"(
    	String email	
     , 	String password	) {
    (new auth.LoginKeyword()).login(
        	email
         , 	password)
}


def static "utils.ScrollKeyword.scrollDown"() {
    (new utils.ScrollKeyword()).scrollDown()
}


def static "search.SearchKeyword.search"(
    	String keyword	) {
    (new search.SearchKeyword()).search(
        	keyword)
}

 /**
	 * Verify có kết quả trả về
	 */ 
def static "search.SearchKeyword.verifyHasResults"() {
    (new search.SearchKeyword()).verifyHasResults()
}

 /**
	 * Verify tất cả kết quả đều chứa keyword
	 */ 
def static "search.SearchKeyword.verifyAllContain"(
    	String keyword	) {
    (new search.SearchKeyword()).verifyAllContain(
        	keyword)
}

 /**
	 * Verify kết quả đầu tiên chứa keyword
	 */ 
def static "search.SearchKeyword.verifyTop1Contains"(
    	String keyword	) {
    (new search.SearchKeyword()).verifyTop1Contains(
        	keyword)
}

 /**
	 * Verify không có kết quả (trường hợp tìm kiếm không có kết quả)
	 */ 
def static "search.SearchKeyword.verifyNoResult"() {
    (new search.SearchKeyword()).verifyNoResult()
}

 /**
	 * Verify số lượng kết quả (nếu cần)
	 */ 
def static "search.SearchKeyword.verifyResultCount"(
    	int expectedCount	) {
    (new search.SearchKeyword()).verifyResultCount(
        	expectedCount)
}
