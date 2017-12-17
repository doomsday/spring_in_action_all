package org.drpsy.spittr.web.exceptions;

import org.drpsy.spittr.web.exceptions.DuplicateSpittleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by drpsy on 15-Dec-17 (00:18).
 * ControllerAdvice-annotated class are applied globally across all @RequestMapping-annotated methods on all
 * controllers in an application. Gathers all @ExceptionHandler methods in a single class so that exceptions from
 * all controllers are handled consistently in one place.
 */
@ControllerAdvice
public class AppWideExceptionHandler {

  @ExceptionHandler(DuplicateSpittleException.class)
  public String duplicateSpittleHandler() {
    return "error/duplicate";
  }

}
