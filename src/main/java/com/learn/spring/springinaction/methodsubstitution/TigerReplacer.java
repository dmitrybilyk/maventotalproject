package com.learn.spring.springinaction.methodsubstitution;

import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;
public class TigerReplacer implements MethodReplacer {
public Object reimplement(Object target, Method method, // Подмена
Object[] args) throws Throwable {
// метода
return "A ferocious tiger";
}
}