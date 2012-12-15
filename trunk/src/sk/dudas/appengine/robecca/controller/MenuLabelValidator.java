package sk.dudas.appengine.robecca.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sk.dudas.appengine.robecca.domain.MenuLabel;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 14.12.2012
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public class MenuLabelValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return MenuLabel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        MenuLabel menuLabel = (MenuLabel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order", "required", "required");
    }
}
