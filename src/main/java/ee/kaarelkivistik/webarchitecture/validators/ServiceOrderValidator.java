package ee.kaarelkivistik.webarchitecture.validators;

import ee.kaarelkivistik.webarchitecture.models.ServiceOrder;
import ee.kaarelkivistik.webarchitecture.models.ServicePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Objects;

/**
 * Created by kaarel on 31.05.16.
 */
public class ServiceOrderValidator implements Validator {

    ServicePartValidator servicePartValidator = new ServicePartValidator();

    @Override
    public boolean supports(Class<?> clazz) {
        return ServiceOrder.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceOrder serviceOrder = (ServiceOrder) target;

        // Ärireegel 1
        if(Objects.equals(serviceOrder.getStatusType().getId(), 2)
                && serviceOrder.getServiceDevices().parallelStream().anyMatch(serviceDevice -> {
                       return !Objects.equals(serviceDevice.getStatusType().getId(), 2);
                    }))
            errors.rejectValue("statusType", "serviceDevicesNotReady", "Tellimuses on remontimata seadmeid");

        // Ärireegel 2
        if(serviceOrder.getServiceDevices().size() == 0
                && !Objects.equals(serviceOrder.getStatusType().getId(), 1))
            errors.rejectValue("statusType", "noServiceDevicesInServiceOrder", "Tellimuses pole ühtegi seadet");

        List<ServicePart> serviceParts = serviceOrder.getServiceParts();

        for(int i = 0; i < serviceParts.size(); i++) {
            errors.pushNestedPath("serviceParts[" + i + "]");
            servicePartValidator.validate(serviceParts.get(i), errors);
            errors.popNestedPath();
        }
    }

}
