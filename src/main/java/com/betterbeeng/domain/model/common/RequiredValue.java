package com.betterbeeng.domain.model.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public abstract class RequiredValue<T> {
  @NonNull
  private final T value;

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + value + ']';
  }
}
