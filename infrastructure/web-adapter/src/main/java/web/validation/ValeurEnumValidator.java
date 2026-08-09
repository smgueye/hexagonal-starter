package web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ValeurEnumValidator implements ConstraintValidator<ValeurEnum, String> {

  private Set<String> valeursAuthorisees;

  @Override
  public void initialize(ValeurEnum contraintes) {
    valeursAuthorisees = Arrays.stream(contraintes.value().getEnumConstants())
      .map(Enum::name)
      .map(String::toLowerCase)
      .collect(Collectors.toUnmodifiableSet());
  }

  @Override
  public boolean isValid(String valeur, ConstraintValidatorContext context) {
    if (valeur == null) {
      return true;
    }
    return valeursAuthorisees.contains(valeur);
  }
}
