package com.university.examination.util.valid.aspect;

import com.university.examination.util.constant.LabelKey;
import com.university.examination.exception.CustomException;
import com.university.examination.util.valid.Validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.university.examination.util.DataUtil.*;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAspect implements ConstraintValidator<Validation, Object> {
    private String label;
    private Boolean required;
    private Integer minLength;
    private Integer maxLength;
    private String regex;
    private Double min;
    private Double max;
    private Double maxSize;
    
    @Override
    public void initialize(Validation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.label = constraintAnnotation.label();
        this.required = constraintAnnotation.required();
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.regex = constraintAnnotation.regex();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.maxSize = constraintAnnotation.maxSize();
    }
    
    @Override
    public boolean isValid(Object data, ConstraintValidatorContext constraintValidatorContext) {
        String errorCode = "";
        List<Object> listErrorField = new ArrayList<>();
        listErrorField.add(this.label);
        
        if (this.required) {
            boolean nullEmpty = false;
            
            if (data instanceof List) {
                nullEmpty = isNullOrEmpty((List) data);
            }
            
            if (!nullEmpty) nullEmpty = isNullObject(data) || isNullOrEmpty(safeToString(data));
            
            if (nullEmpty) {
                errorCode = LabelKey.ERROR_REQUIRED;
            }
        }
        
        String dataChar = safeToString(data);
        if (isNullOrEmpty(errorCode) && this.minLength > -1 && dataChar.length() < this.minLength) {
            errorCode = LabelKey.ERROR_MIN_LENGTH;
            listErrorField.add(this.minLength);
        }
        
        if (isNullOrEmpty(errorCode) && this.maxLength > -1 && dataChar.length() > this.maxLength) {
            errorCode = LabelKey.ERROR_MAX_LENGTH;
            listErrorField.add(this.maxLength);
        }
        
        if (LabelKey.LABEL_FILE_NAME.equalsIgnoreCase(this.label)) {
            dataChar = toSlug(dataChar, false);
        }
        if (isNullOrEmpty(errorCode) && !isNullOrEmpty(this.regex) && (this.required || !isNullOrEmpty(dataChar))) {
            if (!dataChar.matches(this.regex)) {
                errorCode = LabelKey.ERROR_FORMAT;
            }
        }
        
        if (isNullOrEmpty(errorCode) && LabelKey.LABEL_DATA_BASE64.equalsIgnoreCase(this.label)) {
            double base64Size = getBase64Size(dataChar);
            if (base64Size > this.maxSize) {
                errorCode = LabelKey.ERROR_FILE_UPLOAD_MAX_SIZE;
                listErrorField.add(0, this.maxSize);
            }
        }
        
        if (isNullOrEmpty(errorCode)
                && !(data instanceof String)
                && !isNullObject(data)
                && isNumber(dataChar)
                && this.min != Double.MIN_VALUE
                && safeToDouble(data) < this.min
        ) {
            errorCode = LabelKey.ERROR_MIN;
            listErrorField.add(this.min);
        }
        
        if (isNullOrEmpty(errorCode)
                && !(data instanceof String)
                && !isNullObject(data)
                && isNumber(dataChar)
                && this.max != Double.MAX_VALUE
                && safeToDouble(data) > this.max
        ) {
            errorCode = LabelKey.ERROR_MAX;
            listErrorField.add(this.max);
        }
        
        CustomException exception = new CustomException(
                errorCode
        );
        
        if (!isNullOrEmpty(errorCode)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(objectToJson(exception))
                    .addConstraintViolation();
            
            return false;
        }
        
        return true;
    }
}
