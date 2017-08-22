package br.com.marketpay.validaapp.util.components.transacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anotação responsável por demarcar classes, métodos ou annotações transacionais.
 * 
 * @author andremarllus
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public @interface Transacional {
}