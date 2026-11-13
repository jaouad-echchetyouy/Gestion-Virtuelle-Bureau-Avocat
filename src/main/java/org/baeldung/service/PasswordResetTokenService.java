package org.baeldung.service;

import org.baeldung.persistence.model.PasswordResetToken;
import org.baeldung.persistence.model.User;
import org.baeldung.persistence.dao.PasswordResetTokenRepository;
import org.baeldung.persistence.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public PasswordResetToken createPasswordResetTokenForUser(User user) {
        String token = generateToken(); // Vous pouvez générer un token aléatoire ici
        PasswordResetToken resetToken = new PasswordResetToken(token, user);
        return passwordResetTokenRepository.save(resetToken);
    }

    @Transactional
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Transactional
    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword); // Remplacez ceci par votre logique de mise à jour du mot de passe
        userRepository.save(user);
    }

    @Transactional
    public String generateToken() {
        // Implémentation de la génération du token, peut être une valeur aléatoire
        return java.util.UUID.randomUUID().toString();
    }
}

