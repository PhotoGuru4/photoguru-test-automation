
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


def static "search.SearchKeyword.verifyHasResults"() {
    (new search.SearchKeyword()).verifyHasResults()
}


def static "search.SearchKeyword.verifyContainsAll"(
    	String keyword	) {
    (new search.SearchKeyword()).verifyContainsAll(
        	keyword)
}


def static "search.SearchKeyword.verifyNoResult"() {
    (new search.SearchKeyword()).verifyNoResult()
}


def static "search.SearchKeyword.verifyPriorityTop"(
    	String keyword	
     , 	int topN	) {
    (new search.SearchKeyword()).verifyPriorityTop(
        	keyword
         , 	topN)
}


def static "search.SearchKeyword.verifyPriorityTop"(
    	String keyword	) {
    (new search.SearchKeyword()).verifyPriorityTop(
        	keyword)
}
