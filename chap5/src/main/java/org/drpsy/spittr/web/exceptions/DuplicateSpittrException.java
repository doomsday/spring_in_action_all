package org.drpsy.spittr.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by drpsy on 19-Dec-17 (23:56).
 */
@ResponseStatus(
    value = HttpStatus.CONFLICT,
    reason = "Duplicate Spittr detected"
)
public class DuplicateSpittrException extends RuntimeException {

}
