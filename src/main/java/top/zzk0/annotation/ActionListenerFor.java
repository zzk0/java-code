package top.zzk0.annotation;

import java.awt.event.ActionListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListenerFor {

    Class<? extends ActionListener> listener();
}
