package org.drpsy.spittr.web.services;

import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.drpsy.spittr.web.EmailForm;

/**
 * Created by drpsy on 05-Nov-18 (01:25).
 */
public interface SpitterEmailService {

  void sendSimpleSpittleEmail(EmailForm emailForm);

}
