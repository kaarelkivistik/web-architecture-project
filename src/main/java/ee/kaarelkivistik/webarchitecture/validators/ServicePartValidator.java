package ee.kaarelkivistik.webarchitecture.validators;

import ee.kaarelkivistik.webarchitecture.models.ServicePart;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by kaarel on 31.05.16.
 */

public class ServicePartValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ServicePart.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServicePart servicePart = (ServicePart) target;

        // Ärireegel 3
        if(servicePart.getSerialNumber().length() > 0
                && servicePart.getPartCount() != 1)
            errors.rejectValue("partCount", "partWithSerialNumberHasWrongCount", "Seerianumbriga varuosa saab olla ainult üks");
    }
}
