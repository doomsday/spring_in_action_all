package org.drpsy.spittr.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by drpsy on 14-Dec-17 (21:58).
 */
@ResponseStatus(
    value = HttpStatus.CONFLICT,
    reason = "Duplicate Spittle detected"
)
public class DuplicateSpittleException extends RuntimeException {

}
