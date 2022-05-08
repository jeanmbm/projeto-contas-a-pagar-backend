package br.com.unialfa.contasapagar.user.business;

import br.com.unialfa.contasapagar.enuns.Status;
import br.com.unialfa.contasapagar.user.domain.User;
import br.com.unialfa.contasapagar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserBusiness {

    private final UserRepository userRepository;

    @Autowired
    public UserBusiness(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (isValidUsername(user.getUsername()) && user.getPassword() != null && !user.getPassword().equalsIgnoreCase("")) {
            // if (isEmail(user.getUsername) && user.getPassword != null && user.length() > 0) {
            userRepository.save(user);
        } else {
            System.err.println("ERRO AO REGISTRAR USUARIO");
        }
    }

    public void editUser(User user) {
        userRepository.save(user);
    }

    public void disableUser(User user) {
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
    }

    public static boolean isValidUsername(String username) {
        return isEmail(username) || isCPF(username);
    }

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("22222222222") ||
                CPF.equals("33333333333") || CPF.equals("11111111111") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (InputMismatchException error) {
            return (false);
        }
    }


    public static boolean isEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

}
