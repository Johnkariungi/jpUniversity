package jpUniversity.jpUniversity.service;

import jpUniversity.jpUniversity.domain.User;
import jpUniversity.jpUniversity.domain.security.PasswordResetToken;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token) {
        void createPasswordResetTokenForUser(final User user, final String token);
    }
}
