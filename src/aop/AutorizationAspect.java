package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.swing.*;

@Aspect
public class AutorizationAspect {

    @Pointcut("execution(void SpaceWars.main(..))")
    public void AuthenticationEvents() {}

    @Around("AuthenticationEvents()")
    public void authenticateEvents(ProceedingJoinPoint jp) throws Throwable
    {
        JPasswordField pass = new JPasswordField(10);
        JPanel panel = createLogInPanel(pass, "Enter a password:");
        String[] options = new String[]{"OK", "Cancel"};

        int option = JOptionPane.showOptionDialog(null, panel, "Authentication Mode",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);

        while (option == 0)
        {
            char[] password = pass.getPassword();
            String passString = new String(password);
            boolean rightPass = passString.equals("123");

            if (rightPass)
            {
                jp.proceed();
                break;
            }
            else
            {
                JPanel panelAnotherChance = createLogInPanel(pass, "The password is incorrect, please try again:");
                option = JOptionPane.showOptionDialog(null, panelAnotherChance, "Authentication Mode",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[1]);
            }
        }
    }

    private JPanel createLogInPanel(JPasswordField pass, String s) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(s);
        panel.add(label);
        panel.add(pass);
        return panel;
    }


}
