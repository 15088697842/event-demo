package com.success.bigevent.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListConvertUtil {

    public static <S, T> List<T> convertList(List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        for (S sourceObject : sourceList) {
            T targetObject;
            try {
                targetObject = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("转换失败.", e);
            }

            copyProperties(sourceObject, targetObject);

            targetList.add(targetObject);
        }
        return targetList;
    }

    public static <S, T> void copyProperties(S sourceObject, T targetObject) {
        Class<?> sourceClass = sourceObject.getClass();
        Class<?> targetClass = targetObject.getClass();

        Field[] sourceFields = getAllFields(sourceClass);
        for (Field sourceField : sourceFields) {
            if(sourceField.getName().equals("serialVersionUID"))
                continue;

            try {
                Field targetField = null;
                try {
                    sourceField.setAccessible(true);
                    targetField = targetClass.getDeclaredField(sourceField.getName());
                } catch (NoSuchFieldException e) {
                    // 如果在当前类找不到对应字段，尝试在父类中查找
                    targetField = getFieldInHierarchy(targetClass, sourceField.getName());
                }
                if (targetField != null) {
                    targetField.setAccessible(true);
                    sourceField.setAccessible(true);

                    if(targetField.getName().equals("serialVersionUID"))
                        continue;

                    Object value = sourceField.get(sourceObject);
                    targetField.set(targetObject, value);
                }
            } catch (IllegalAccessException e) {
            }
        }
    }

    /**
     * 循环查询父类的属性
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getFieldInHierarchy(Class<?> clazz, String fieldName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 递归获取自己 + 父类的所有属性
     * @param clazz
     * @return
     */
    private static Field[] getAllFields(Class<?> clazz) {
        if (clazz == null || clazz == Object.class) {
            return new Field[0];
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] parentFields = getAllFields(clazz.getSuperclass());
        Field[] allFields = new Field[declaredFields.length + parentFields.length];
        System.arraycopy(declaredFields, 0, allFields, 0, declaredFields.length);
        System.arraycopy(parentFields, 0, allFields, declaredFields.length, parentFields.length);

        return allFields;
    }
}
