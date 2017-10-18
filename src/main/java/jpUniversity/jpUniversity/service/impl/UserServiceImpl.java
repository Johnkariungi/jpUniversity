package jpUniversity.jpUniversity.service.impl;

import jpUniversity.jpUniversity.domain.User;
import jpUniversity.jpUniversity.domain.security.PasswordResetToken;
import jpUniversity.jpUniversity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

}
