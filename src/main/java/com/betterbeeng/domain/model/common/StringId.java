package com.betterbeeng.domain.model.common;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
public abstract class StringId extends EntityId<String> {

  public StringId(String value) {
    super(value);
    validateValue(value);
  }

  private void validateValue(String value) {
    if (StringUtils.isBlank(value)) {
      throw new IllegalArgumentException("'id' should not be empty");
    }
  }
}
