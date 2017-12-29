package org.drpsy.spittr.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by drpsy on 14-Dec-17 (21:39).
 */

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "Spittle not found")
public class SpittleNotFoundException extends RuntimeException {

}
