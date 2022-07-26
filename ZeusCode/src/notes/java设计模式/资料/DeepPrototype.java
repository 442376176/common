package com.atguigu.prototype.deepclone;

/**
 * ��ȿ�����ʵ�ֿ��������ַ�ʽ
 * @author Administrator
 *
 */
public class DeepPrototype implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String name;

	public DeepCloneableTarget deepCloneableTarget;

	public DeepPrototype() {

	}

	// ��ʽ1 ��ͨ����дclone������ʵ����� �����ö���࣬���ַ����ȽϷ�����
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object deep = null;
		try {
			deep = super.clone();//������ɶԻ����������͵Ŀ�¡
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		DeepPrototype deepPrototype = (DeepPrototype) deep;
		//���������ͣ���Ҫ��������
		deepPrototype.deepCloneableTarget = (DeepCloneableTarget) deepPrototype.deepCloneableTarget.clone();
		return deepPrototype;
	}

	// ��ʽ2�� ͨ���������л�ʵ����� ���Ƽ���
	public Object deepClone() {
		 //��ȿ�¡
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //return super.clone();//Ĭ��ǳ��¡��ֻ��¡�˴�����������ͺ�String
            //���л�
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            
            //�����л�
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepPrototype copy = (DeepPrototype)ois.readObject();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
