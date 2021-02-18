package top.zzk0.annotation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class Window {

    private JFrame mainWin = new JFrame("标题");

    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("确定");

    @ActionListenerFor(listener = CancelListener.class)
    private JButton cancel = new JButton("取消");

    public void init() {
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
        ActionListenerInstaller.processAnnotations(this);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

}

class OkListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "ok");
    }
}

class CancelListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "cancel");
    }
}

class ActionListenerInstaller {
    public static void processAnnotations(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(ActionListenerFor.class)) {
                    field.setAccessible(true);
                    ActionListenerFor a = field.getAnnotation(ActionListenerFor.class);

                    // 这个 get 拿到的是这个 field 对应的对象
                    Object button = field.get(obj);

                    // instanceof 可以省略 null 的判断
                    if (a != null && button instanceof AbstractButton) {
                        Class<? extends ActionListener> listenerClass = a.listener();
                        ActionListener actionListener = listenerClass.newInstance();
                        AbstractButton abstractButton = (AbstractButton)button;
                        abstractButton.addActionListener(actionListener);
                    }
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
