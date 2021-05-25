package mercadoLivre.configs.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

   private String domainAttribute;
   private Class<?> aClass;

   @Autowired
   private EntityManager manager;

   public void initialize(UniqueValue constraintAnnotation) {
      ConstraintValidator.super.initialize(constraintAnnotation);
      domainAttribute = constraintAnnotation.fieldName();
      aClass = constraintAnnotation.domainClass();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
       Query query = manager.createQuery("select 1 from " + aClass.getName() + " where " + domainAttribute + "=:value");
       query.setParameter("value", value);
       List<?> list = query.getResultList();

       Assert.state(list.size() <= 1, "Foi encontrado mais de um " + aClass + "com o atributo " + domainAttribute + " = " + value);

       return list.isEmpty();
    }
}
