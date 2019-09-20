package com.example.helpers;

import com.example.service.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) value;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
