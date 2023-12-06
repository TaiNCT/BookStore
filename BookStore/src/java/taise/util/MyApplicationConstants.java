/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.util;

/**
 *
 * @author ADMIN
 */
public class MyApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateController";
        public static final String START_UP_CONTROLLER = "startupController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToListController";
        public static final String VIEW_CART_PAGE = "viewcartPage";
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String REMOVE_ITEM_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String BOOK_LIST_CONTROLLER = "bookListController";
    }

    public class LoginFeature {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String ERROR_PAGE = "invalidPage";
    }

    public class LogoutFeature {

        public static final String LOGIN_PAGE = "loginPage";
    }

    public class SearchLastNameFeature {

        public static final String SEARCH_PAGE = "searchPage";
    }

    public class DeleteAccountFeture {

        public static final String ERROR_PAGE = "deleteErr";
    }

    public class CreateAccountFeture {

        public static final String CREATE_ERROR_PAGE = "createAccountPage";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class ShowBookFeture {

        public static final String BOOK_LIST = "bookStore";
    }

    public class StartUpFeture {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class UpdateAccountFeture {

        public static final String UPDATE_ERROR = "updateErr";
    }
}
