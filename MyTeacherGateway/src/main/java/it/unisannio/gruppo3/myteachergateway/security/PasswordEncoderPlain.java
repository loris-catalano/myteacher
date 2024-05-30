package it.unisannio.gruppo3.myteachergateway.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderPlain implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
