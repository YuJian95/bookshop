package common;

/**
 * 工厂方法，用来获取对应的配置的对象
 */

public class BsFactory {

    public static Object getBean(String name) {

        try {
            String className = BsConfig.properties.getProperty(name);
            return Class.forName(className).newInstance();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
