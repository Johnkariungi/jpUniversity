package jpUniversity.jpUniversity.repository;

import jpUniversity.jpUniversity.domain.User;
import jpUniversity.jpUniversity.domain.security.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    /*method to delete all expired tokens*/
    @Modifying
    @Query("delete from PasswordResetToken t where t.expirydate <= ?1")
    void deleteAllExpiredSince(Date now);
}
