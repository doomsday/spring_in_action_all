package org.drpsy.spittr.security;

import java.io.Serializable;
import org.drpsy.spittr.data.neo4j.documents.Spittle;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Custom permission evaluator.
 *
 * Created by drpsy on 16-May-18 (22:49).
 */
public class SpittlePermissionEvaluator implements PermissionEvaluator {

  private static final GrantedAuthority ADMIN_AUTHORITY = new SimpleGrantedAuthority("ROLE_ADMIN");

  /**
   * Checks to see that the object being evaluated is a Spittle and that you're checking for delete permission. If so,
   * it checks that the Spitter's username is equal to the authenticated user's name or that the current
   * authentication has ROLE_ADMIN authority.
   *
   * Need to register it with Spring Security (see MethodSecurityConfig::createExpressionHandler())
   *
   * @param authentication
   * @param target
   * @param permission
   * @return
   */
  @Override
  public boolean hasPermission(Authentication authentication, Object target, Object permission) {

    if (authentication == null || target == null || !(permission instanceof String)) {
      return false;
    }

    if (target instanceof Spittle) {
      Spittle spittle = (Spittle) target;
      String spittleAuthorUsername = spittle.getAuthor().getUsername();
      if ("delete".equals(permission)) {
        return isAdmin(authentication) || spittleAuthorUsername.equals(authentication.getName());
      }
    }

    throw new UnsupportedOperationException("hasPermission not supported for object <" + target + "> and "
        + "permission <" + permission + ">");
  }

  /*
   * Useful when only the ID of the target object is available.
   */
  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
      Object permission) {
    throw new UnsupportedOperationException();
  }

  private boolean isAdmin(Authentication authentication) {
    return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
  }
}
