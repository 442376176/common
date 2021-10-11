package com.zcc.utils.updateClass;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @Author zcc
 * @Date 2021/7/8 11:07
 * @Version 1.0
 */
public class AddFieldAdapter extends ClassAdapter {
    private int accessModifier;
    private String name;
    private String desc;
    private boolean isFieldPresent;

    public AddFieldAdapter(ClassVisitor cv, int accessModifier, String name, String desc) {
        super(cv);
        this.accessModifier = accessModifier;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        if (name.equals(this.name)) {
            isFieldPresent = true;
        }
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isFieldPresent) {
            FieldVisitor fv = cv.visitField(accessModifier, name, desc, null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
    @Override
    public MethodVisitor visitMethod(int var1, String var2, String var3, String var4, String[] var5) {
        return this.cv.visitMethod(var1, var2, var3, var4, var5);
    }
} 